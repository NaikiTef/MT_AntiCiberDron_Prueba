package MTDataAccessCompoment.MTHelpers;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import MTInfrastructureComponent.MTAppConfig;
import MTInfrastructureComponent.MTAppDataException;
import MTDataAccessCompoment.MTInterfaces.MTIDataHelperSQLiteDAO;


public class MTDataHelperSQLiteDAO<T> implements MTIDataHelperSQLiteDAO<T> {
    protected final Class<T> dtoClass;
    protected final String tableName;
    protected final String tablePK;

    // Usar el path JDBC correcto para SQLite
    private static final String DB_PATH = "jdbc:sqlite:" + MTAppConfig.MTDATABASE.replace("\\", "/");
    private static Connection conn = null;


    protected static synchronized Connection openConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DB_PATH);
        }
        return conn;
    }

    protected static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    protected String getDataTimeNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Construye la relacion entre la clase DTO y la tabla de la base de datos
     * @param dtoClass  : Nombre de la clase DTO
     * @param tableName : Nombre de la tabla
     * @param tablePK   : Nombre del PK de la tabla
     * @throws AppException: Error al asociar la clase con la tabla
     */

    public MTDataHelperSQLiteDAO(Class<T> dtoClass, String tableName, String tablePK) throws MTAppDataException {
        try {
            openConnection();
        } catch (SQLException e) {
            throw new MTAppDataException(e, getClass(), "DataHelperSQLiteDAO");
        }
        this.dtoClass  = dtoClass;
        this.tableName = tableName;
        this.tablePK   = tablePK;
    }

    @Override
    public boolean create(T entity) throws MTAppDataException {
        Field[] fields = dtoClass.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            // Excluir PK y campos por defecto y auditoria
            if (!name.equalsIgnoreCase(tablePK)
                && !name.equalsIgnoreCase("Estado")
                && !name.equalsIgnoreCase("FechaCreacion")
                && !name.equalsIgnoreCase("FechaModifica")) {
                columns.append(name).append(",");
                placeholders.append("?,");
            }
        }

        // Eliminar la última coma
        String cols = columns.substring(0, columns.length() - 1);
        String vals = placeholders.substring(0, placeholders.length() - 1);

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, cols, vals);

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            int index = 1;
            for (Field field : fields) {
                String name = field.getName();
                if (!name.equalsIgnoreCase(tablePK)
                    && !name.equalsIgnoreCase("Estado")
                    && !name.equalsIgnoreCase("FechaCreacion")
                    && !name.equalsIgnoreCase("FechaModifica")) 
                        stmt.setObject(index++, field.get(entity));
            }
            return (stmt.executeUpdate() > 0);
        } catch (SQLException | IllegalAccessException e) {
            throw new MTAppDataException(e, getClass(), "create");
        }
    }

    @Override
    public boolean update(T entity) throws MTAppDataException {
        try {
            Field[] fields = dtoClass.getDeclaredFields();
            StringBuilder updates = new StringBuilder();
            Object pkValue = null;

            for (Field field : fields) {
                String name = field.getName();

                if (!name.equalsIgnoreCase(tablePK)) {
                    updates.append(name).append(" = ?, ");
                } else {
                    if (!field.canAccess(entity)) {
                        field.setAccessible(true);
                    }
                    pkValue = field.get(entity);
                }
            }

            updates.append("FechaModifica = ?"); // campo técnico de auditoría

            String sql = String.format("UPDATE %s SET %s WHERE %s = ?", tableName, updates, tablePK);

            try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
                int index = 1;
                for (Field field : fields) {
                    String name = field.getName();
                    if (!name.equalsIgnoreCase(tablePK)) {
                        if (!field.canAccess(entity)) {
                            field.setAccessible(true);
                        }
                        stmt.setObject(index++, field.get(entity));
                    }
                }

                stmt.setString(index++, getDataTimeNow()); // FechaModifica
                stmt.setObject(index, pkValue); // WHERE PK = ?

                return stmt.executeUpdate() > 0;
            }

        }   catch (SQLException | IllegalAccessException e) {
            throw new MTAppDataException(e, getClass(), "update");
        }
    }

    @Override
    public boolean delete(Integer id) throws MTAppDataException {
        String sql = String.format("UPDATE %s SET Estado = ?, FechaModifica = ? WHERE %s = ?", tableName, tablePK);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setString(1, "X");
            stmt.setString(2, getDataTimeNow());
            stmt.setInt   (3, id);
            return stmt.executeUpdate() > 0;
        }catch (SQLException e) {
            throw new MTAppDataException(e, getClass(), "delete");
        }
    }

    @Override
    public T readBy(Integer id) throws MTAppDataException {
        String sql = String.format("SELECT * FROM %s WHERE %s = ? AND Estado = 'A'", tableName, tablePK);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToEntity(rs) : null;
            }
        }catch (SQLException e) {
            throw new MTAppDataException(e, getClass(), "readBy");
        }
    }

    @Override
    public List<T> readAll() throws MTAppDataException {
        List<T> list = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s WHERE Estado = 'A'", tableName);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new MTAppDataException(e, getClass(), "readAll");
        }
        return list;
    }

    @Override
    public Integer getMaxReg() throws MTAppDataException {
        String sql = String.format("SELECT COUNT(*) FROM %s WHERE Estado = 'A'", tableName);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new MTAppDataException(e, getClass(), "getMaxReg");
        }
    }

    protected T mapResultSetToEntity(ResultSet rs) throws MTAppDataException {
        try {
            T instance = dtoClass.getDeclaredConstructor().newInstance();
            ResultSetMetaData meta = rs.getMetaData();

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String col = meta.getColumnLabel(i); // usa alias si existen
                Object val = rs.getObject(i);

                Field field = dtoClass.getDeclaredField(col);
                if (!field.canAccess(instance)) {
                    field.setAccessible(true);
                }
                field.set(instance, val);
            }
            return instance;
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new MTAppDataException(e, getClass(), "mapResultSetToEntity");
        }
    }

}
