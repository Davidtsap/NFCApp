package Data;

public class User {
    String userName;
    String password;
    String eMail;
    String phone;

    public void setUserName(String userName){
        this.userName =userName;
    }
   public void setPassword(String password){
    this.password =password;
   }
   public void seteMail(String eMail){
       this.eMail = eMail;
   }
   public void setPhone(String phone){
       this.phone =phone;
   }
   public String getUserName(){
       return this.userName;
   }
   public String getPassword(){
       return this.password;
   }
   public String geteMail(){
       return this.eMail;
   }
   public String getPhone(){
       return this.phone;
   }

}


