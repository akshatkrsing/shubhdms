package Server.table;

public class BulletinTable {
    public static final String TABLE_NAME="bulletins";
    public static final String COLUMN_BULLETIN = "bulletinID";
    public static final String COLUMN_IMAGE_BLOB = "image";
    public static final String COLUMN_BULLETIN_CAPTION ="caption";
    public static final String COLUMN_BULLETIN_TIMESTAMP = "bulletinTime";
    public static final String COLUMN_BULLETIN_TOPIC = "topic";
    public static final String QUERY_FETCH_BULLETIN  = "SELECT * FROM "+TABLE_NAME;
}
