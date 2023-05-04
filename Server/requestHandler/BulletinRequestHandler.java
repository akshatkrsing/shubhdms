package Server.requestHandler;

import Server.entity.Bulletin;
import Server.response.BulletinResponse;
import Server.table.BulletinTable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class BulletinRequestHandler extends RequestHandler{

    Connection connection;
    ObjectOutputStream oos;

    public BulletinRequestHandler(Connection connection, ObjectOutputStream oos) {
        this.connection = connection;
        this.oos = oos;
    }

    @Override
    public void sendResponse(String userID) {
        ArrayList<Bulletin> bulletinArrayList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(BulletinTable.QUERY_FETCH_BULLETIN);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                bulletinArrayList.add(
                        new Bulletin(resultSet.getBlob(BulletinTable.COLUMN_IMAGE_BLOB),
                                resultSet.getString(BulletinTable.COLUMN_BULLETIN_CAPTION),
                                resultSet.getTimestamp(BulletinTable.COLUMN_BULLETIN_TIMESTAMP),
                                resultSet.getString(BulletinTable.COLUMN_BULLETIN_TOPIC))
                );
            }
            oos.writeObject(new BulletinResponse(bulletinArrayList));
            oos.flush();
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
