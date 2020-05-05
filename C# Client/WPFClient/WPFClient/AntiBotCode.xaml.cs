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
    /// Interaction logic for AntiBotCode.xaml
    /// </summary>
    public partial class AntiBotCode : Window
    {
        public AntiBotCode()
        {
            InitializeComponent();
        }

        private void CheckCode_Click(object sender, RoutedEventArgs e)
        {
            if (this.code.Text == "HE3X6")
            {
                MainWindow mainWindow = new MainWindow();
                mainWindow.Show();

                this.Close();
            }
            else
            {
                MessageBox.Show("Nieprawidłowy kod!", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
