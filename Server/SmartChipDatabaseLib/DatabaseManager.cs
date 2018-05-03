using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using MySql.Data.MySqlClient;
using System.Windows.Forms;

namespace SmartChipDatabaseLib
{
    public class DatabaseManager
    {
        public static string MyConnection ;

        
        public DatabaseManager()
        {
            MyConnection = "datasource=localhost;port=3306;username=root;password=admin;database=mydb";

        }
        public string get_ChipAction(int chip_ID_Wanted)
        {
            string chipaction = " ";

            try
            {
                string Query = "SELECT * FROM chips WHERE chip_ID='" + chip_ID_Wanted.ToString() + "';";
                MySqlConnection MyConn2 = new MySqlConnection(MyConnection);
                MySqlCommand MyCommand2 = new MySqlCommand(Query, MyConn2);
                MySqlDataReader MyReader;
                MyConn2.Open();
                MyReader = MyCommand2.ExecuteReader();

                if(MyReader.Read())
                {
                    chipaction = MyReader.GetString("ChipAction");  
                }
                MyConn2.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("EXEPTION " + ex.Message);
            }
            return chipaction;
        }
        public void insert_Chip(int chip_ID, int user_ID, string chipAction)
        {
            try
            {

                string Query = "INSERT into chips(chip_ID,user_ID,ChipAction) values('" + chip_ID.ToString() + "','" +user_ID.ToString()+ "','" + chipAction+ "');";
                MySqlConnection MyConn2 = new MySqlConnection(MyConnection);
                MySqlCommand MyCommand2 = new MySqlCommand(Query, MyConn2);
                MySqlDataReader MyReader2;
                MyConn2.Open();
                MyReader2 = MyCommand2.ExecuteReader();  
                while (MyReader2.Read()){ }
                MyConn2.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("EXEPTION " + ex.Message);
            }
        }
        //public static void update_data()
        //{
        //    try
        //    {
        //        string MyConnection2 = "datasource=localhost;port=3307;username=root;password=root";
        //        string Query = "update student.studentinfo set idStudentInfo='" + this.IdTextBox.Text + "',Name='" + this.NameTextBox.Text + "',Father_Name='" + this.FnameTextBox.Text + "',Age='" + this.AgeTextBox.Text + "',Semester='" + this.SemesterTextBox.Text + "' where idStudentInfo='" + this.IdTextBox.Text + "';";
        //        MySqlConnection MyConn2 = new MySqlConnection(MyConnection2);
        //        MySqlCommand MyCommand2 = new MySqlCommand(Query, MyConn2);
        //        MySqlDataReader MyReader2;
        //        MyConn2.Open();
        //        MyReader2 = MyCommand2.ExecuteReader();
        //        MessageBox.Show("Data Updated");
        //        while (MyReader2.Read())
        //        {
        //        }
        //        MyConn2.Close();//Connection closed here  
        //    }
        //    catch (Exception ex)
        //    {
        //        MessageBox.Show(ex.Message);
        //    }
        //}
        public void delete_Chip(int chip_ID_Wanted)
        {
            try
            {
                string Query = "DELETE from chips WHERE chip_ID='" + chip_ID_Wanted.ToString() + "';";
                MySqlConnection MyConn2 = new MySqlConnection(MyConnection);
                MySqlCommand MyCommand2 = new MySqlCommand(Query, MyConn2);
                MySqlDataReader MyReader2;
                MyConn2.Open();
                MyReader2 = MyCommand2.ExecuteReader();
                MessageBox.Show("Data Deleted");
                while (MyReader2.Read()) { }
                MyConn2.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("EXEPTION "+ex.Message);
            }
        }

    }
}
