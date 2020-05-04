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
    /// Interaction logic for SignIn_OutShowDialog.xaml
    /// </summary>
    public partial class SignIn_OutShowDialog : Window
    {
        public SignIn_OutShowDialog()
        {
            InitializeComponent();
        }

        private async void loginButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                var logged_user = await DataHelper.client.loginAsync(this.login.Text, this.password.Password);
                DataHelper.loggedUser = logged_user.@return;

                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void register_Click(object sender, RoutedEventArgs e)
        {
            RegisterView registerView = new RegisterView();

            this.Close();
            registerView.ShowDialog();
        }
    }
}
