package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {
    public static long getValue(String sequenceName) throws SQLException {
        long nextValue = -1;
        String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = ConectorBD.getPrepared(conn, sql);
             ResultSet rs = ConectorBD.getSelect(pstmt)) {

            if (rs.next()) {
                nextValue = rs.getLong(1);
            }
        }

        return nextValue;
    }
}
