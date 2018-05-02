using SmartChipServicesLib;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.ServiceModel.Web;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleHost
{
    class Program
    {
        static void Main(string[] args)
        {
            //ServiceHost host = new ServiceHost(typeof(SmartChipService));
            WebServiceHost host = new WebServiceHost(typeof(SmartChipService));
            
            try
            {
                host.Open();
                printServiceInfo(host);
                Console.ReadLine();
                host.Close();
            }
            catch(Exception e)
            {
                Console.WriteLine(e.Message);
                host.Abort();
            }
        }

        static void printServiceInfo(ServiceHost i_Host)
        {
            Console.WriteLine("{0} is up and running with these endpoints:", i_Host.Description.ServiceType);

            foreach(ServiceEndpoint endpoint in i_Host.Description.Endpoints)
            {
                Console.WriteLine(endpoint.Address);
            }
        }
    }
}
