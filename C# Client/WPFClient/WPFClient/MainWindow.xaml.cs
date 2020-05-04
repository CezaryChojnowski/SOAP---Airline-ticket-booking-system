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
using System.Windows.Navigation;
using System.Windows.Shapes;
using WPFClient.TicketBookingServiceReference;

namespace WPFClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public countryDTO[] countryDTOs;
        public TicketBookingClient client;
        public countryDTO[] countriesTo;

        public MainWindow()
        {
            InitializeComponent();

            client = new TicketBookingClient();

            countryDTOs = client.findAllTheCountriesThatPlanesDepartFrom();

            foreach (var item in countryDTOs)
            {
                this.countryFrom.Items.Add(item.name);
            }
        }

        private void countryFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.cityFrom.SelectedItem = null;
            this.cityFrom.Items.Clear();

            foreach (var item in this.countryDTOs)
            {
                if (item.name == this.countryFrom.SelectedItem.ToString())
                {
                    foreach (var cities in item.citiesList)
                    {
                        this.cityFrom.Items.Add(cities.name);
                    }
                }
            }
        }

        private void cityFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.countryTo.SelectedItem = null;
            this.countryTo.Items.Clear();

            if (this.cityFrom.SelectedItem != null)
            {
                this.countriesTo = this.client.findAllCountriesToWhichPlanesDepartFromAgivenCity(this.countryFrom.SelectedItem.ToString(), this.cityFrom.SelectedItem.ToString());

                foreach (var item in this.countriesTo)
                {
                    this.countryTo.Items.Add(item.name);
                }
            }
        }

        private void countryTo_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.cityTo.SelectedItem = null;
            this.cityTo.Items.Clear();

            foreach (var item in this.countriesTo)
            {
                if (this.countryTo.SelectedItem != null && item.name == this.countryTo.SelectedItem.ToString())
                {
                    foreach (var cities in item.citiesList)
                    {
                        this.cityTo.Items.Add(cities.name);
                    }
                }
            }
        }

        private void cityTo_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ShowPotencialOptions();
        }

        public void ShowPotencialOptions()
        {
            if (this.countryFrom.SelectedItem != null && this.cityFrom.SelectedItem != null 
                && this.countryTo.SelectedItem != null && this.cityTo.SelectedItem != null && this.date.SelectedDate != null)
            {
                try
                {
                    var date = Convert.ToDateTime(this.date.Text).ToString("dd-MM-yyyy");
                    var lista = this.client.findFlightsBetweenGivenCities(this.countryFrom.SelectedItem.ToString(),
                                                                      this.cityFrom.SelectedItem.ToString(),
                                                                      this.countryTo.SelectedItem.ToString(),
                                                                      this.cityTo.SelectedItem.ToString(),
                                                                      date);

                    this.listView.ItemsSource = lista;
                }
                catch (Exception e)
                {
                    MessageBox.Show(e.Message);
                }
            }
        }

        private void book_Click(object sender, RoutedEventArgs e)
        {
            var x = (flight)this.listView.SelectedItem;

            if (x != null)
            {
                SignIn_OutShowDialog showDialog = new SignIn_OutShowDialog();
                showDialog.ShowDialog();
            }
        }
    }
}
