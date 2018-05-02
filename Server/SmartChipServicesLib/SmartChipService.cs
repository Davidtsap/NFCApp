using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace SmartChipServicesLib 
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class SmartChipService : ISmartChipService
    {
        public string Testing()
        {
            return "Calling Testing() --> Testing success!!!";
        }





        //private DatabaseManager m_Database = new DatabaseManager();

        public int GetActivityIndexByChipID(string i_ChipID)
        {
            //return m_Database.GetActivityIndexByChipID(i_ChipID);
            return 1234567;
        }

        public List<string> GetUserChipCodeByChipID(string i_ChipID)
        { 
            //return m_Database.GetUserChips(i_ChipID);
            List<string> resList = new List<string>(new string[]{"user1", "user2", "user3"});
            return resList;
        }

        public string SubmitNewUser(string i_UserID, string i_UserEmail, string i_UserPassword)
        {
            //m_Database.AddNewUser(i_UserID, i_UserEmail, i_UserPassword);
            return String.Format("User Submited Successfully --> id: {0} | email: {1} | password: {2}", i_UserID, i_UserEmail, i_UserPassword);
        }

       
        //SmartChip Services <End>


        //Testing
        private List<string> m_UserList = new List<string>();

        public bool DeleteAllUsers()
        {
            m_UserList.Clear();
            bool isListEmpty = m_UserList.Count == 0;

            return isListEmpty;
        }


        public string Echo(string i_STR)
        {
            return "Echo --> " + i_STR;
        }

        public int GetUsersNumber()
        {
            int numOfUsersInList = m_UserList.Count;
            return numOfUsersInList;
        }

        public string SubmitUser(string i_User)
        {
            m_UserList.Add(i_User);

            bool isUserAdded = m_UserList.Contains(i_User);
            string result = null;

            if (isUserAdded)
            {
                result = "Added " + i_User + " to users list.";
            }
            else
            {
                result = "Falid to add " + i_User + " to users list.";
            }

            return result;
            
        }
        //Testing

        
    }
}
