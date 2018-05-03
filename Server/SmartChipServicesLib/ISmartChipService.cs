using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Threading.Tasks;

namespace SmartChipServicesLib
{
    //SmartChip Serices <Start>
    [ServiceContract]
    public interface ISmartChipService
    {
        [WebGet(UriTemplate = "Testing/test", ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        string Testing();


        [WebGet(UriTemplate = "GetActivityIndex?chipID={i_ChipID}",
            ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        int GetActivityIndexByChipID(string i_ChipID);


        //[WebInvoke(Method = "POST", 
        [WebGet(
            UriTemplate = "Submit?userID={i_UserID}&userEmail={i_UserEmail}&userPassword={i_UserPassword}",
            ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        string SubmitNewUser(string i_UserID, string i_UserEmail, string i_UserPassword);


        [WebGet(UriTemplate = "GetChipCode?chipID={i_ChipID}",
            ResponseFormat = WebMessageFormat.Json)]
        List<string> GetUserChipCodeByChipID(string i_ChipID);


        [WebInvoke(Method = "POST", UriTemplate = "SumbitUser/{i_User}")]
        [OperationContract]
        string SubmitUser(string i_User);

        //SmartChip Serices <End>

        //*****************************************************************************
        

        [WebGet(UriTemplate = "GetUsersNumber")]
        [OperationContract]
        int GetUsersNumber();

        [WebGet(UriTemplate = "GetEcho/{i_STR}", ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        string Echo(string i_STR);

        [WebInvoke(Method = "DELETE", UriTemplate = "DeleteAllUsers")]
        [OperationContract]
        bool DeleteAllUsers();
        //*****************************************************************************
    }



}
