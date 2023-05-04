package Server.requestHandler;

import Server.request.GetProfilePicRequest;
import Server.response.GetProfilePicResponse;
import Server.table.PatientTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class GetProfilePicRequestHandler extends RequestHandler {
    Connection connection;
    ObjectOutputStream oos;
    GetProfilePicRequest request;

    public GetProfilePicRequestHandler(Connection connection, ObjectOutputStream oos, GetProfilePicRequest request) {
        this.connection = connection;
        this.oos = oos;
        this.request = request;

    }

    @Override
    public void sendResponse(String userID) {
        GetProfilePicResponse getProfilePicResponse = null;
        try
        {
            PreparedStatement preparedStatement=connection.prepareStatement(PatientTable.SELECT_PROFILE_PIC_STUDENT);
            preparedStatement.setString(1, userID);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()){
                Blob blob=results.getBlob(1);
                ByteArrayInputStream inputStream= (ByteArrayInputStream) blob.getBinaryStream();
                BufferedImage bufferedImage =ImageIO.read(inputStream);
                ImageIcon imageIcon=new ImageIcon(bufferedImage);
                getProfilePicResponse = new GetProfilePicResponse(imageIcon);
            }


        }
        catch (SQLException | IOException e)
        {
            System.out.println(" Error occurred while getting profile pic "+e.getMessage());
        }
        finally
        {
            try
            {
                oos.writeObject(getProfilePicResponse);
                oos.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
