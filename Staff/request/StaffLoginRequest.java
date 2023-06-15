package Staff.request;

import com.example.dispensary_management_system.staff.request.Request;

public class StaffLoginRequest extends Request {
        private String username;
        private String password;

        public StaffLoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

