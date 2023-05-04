package Server.requestHandler;


import Server.entity.DutyChartRow;

import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;

public class DutyChartRequestHandler extends RequestHandler{

    Connection connection;
    ObjectOutputStream oos;

    public DutyChartRequestHandler(Connection connection, ObjectOutputStream oos) {
        this.connection = connection;
        this.oos = oos;
    }

    @Override
    public void sendResponse(String userID) {
        ArrayList<DutyChartRow> dutyChartRowArrayList = new ArrayList<>();
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement();
//            preparedStatement.setString(1,userID);
//
//            System.out.println(preparedStatement);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                dutyChartRowArrayList.add(
//                        new DutyChartRow()
//                );
//            }
//            oos.writeObject(new DutyChartResponse(dutyChartRowArrayList,);
//            oos.flush();
//        }
//        catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
    }
}
