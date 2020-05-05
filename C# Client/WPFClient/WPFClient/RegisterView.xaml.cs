using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace WPFClient
{
    /// <summary>
    /// Interaction logic for RegisterView.xaml
    /// </summary>
    public partial class RegisterView : Window
    {
        public RegisterView()
        {
            InitializeComponent();
        }

        private async void registerAndlogIn_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                var register = await DataHelper.client.registerAsync(new TicketBookingServiceReference.passenger()
                {
                    name = this.name.Text,
                    surname = this.surname.Text,
                    email = this.email.Text,
                    login = this.login.Text,
                    password = this.password.Password
                });

                if (register.@return)
                {
                    var loggedUser = await DataHelper.client.loginAsync(this.login.Text, this.password.Password);
                    DataHelper.loggedUser = loggedUser.@return;

                    this.Close();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
