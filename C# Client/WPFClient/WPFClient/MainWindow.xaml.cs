using System;
using System.ServiceModel;
using System.ServiceModel.Channels;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
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
            DataHelper.client = client;
            var scope = new OperationContextScope(client.InnerChannel);

            try
            {
                MessageHeader antyCode = MessageHeader.CreateHeader("antiBotCode", "http://ws/", "HE3X6", false, "http://schemas.xmlsoap.org/soap/actor/next");
                OperationContext.Current.OutgoingMessageHeaders.Add(antyCode);

                countryDTOs = client.findAllTheCountriesThatPlanesDepartFrom();

                foreach (var item in countryDTOs)
                {
                    this.countryFrom.Items.Add(item.name);
                }
                
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
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

        private async void cityFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.countryTo.SelectedItem = null;
            this.countryTo.Items.Clear();

            if (this.cityFrom.SelectedItem != null)
            {
                try
                {
                    var countries = await this.client.findAllCountriesToWhichPlanesDepartFromAgivenCityAsync(this.countryFrom.SelectedItem.ToString(), this.cityFrom.SelectedItem.ToString());
                    this.countriesTo = countries.@return;

                    foreach (var item in this.countriesTo)
                    {
                        this.countryTo.Items.Add(item.name);
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
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

        private async void cityTo_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            await ShowPotencialOptions();
        }

        public async Task ShowPotencialOptions()
        {
            if (this.countryFrom.SelectedItem != null && this.cityFrom.SelectedItem != null 
                && this.countryTo.SelectedItem != null && this.cityTo.SelectedItem != null && this.date.SelectedDate != null)
            {
                try
                {
                    var date = Convert.ToDateTime(this.date.Text).ToString("dd-MM-yyyy");
                    var list = await this.client.findFlightsBetweenGivenCitiesAsync(this.countryFrom.SelectedItem.ToString(),
                                                                      this.cityFrom.SelectedItem.ToString(),
                                                                      this.countryTo.SelectedItem.ToString(),
                                                                      this.cityTo.SelectedItem.ToString(),
                                                                      date);

                    this.listView.ItemsSource = list.@return;
                }
                catch (Exception e)
                {
                    MessageBox.Show(e.Message);
                }
            }
        }

        private async void book_Click(object sender, RoutedEventArgs e)
        {
            var flight = (flight)this.listView.SelectedItem;

            if (flight != null)
            {
                SignIn_OutShowDialog showDialog = new SignIn_OutShowDialog();
                showDialog.ShowDialog();
            }

            try
            {
                var ticket = await this.client.BookFlightAsync(flight, DataHelper.loggedUser);

                SummaryView summaryView = new SummaryView(ticket.@return);
                summaryView.ShowDialog();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private async void SearchTicket_Click(object sender, RoutedEventArgs e)
        {
            if (!string.IsNullOrEmpty(this.ticketCode.Text) && int.TryParse(this.ticketCode.Text, out int result))
            {
                try
                {
                    var ticket = await this.client.checkReservationAsync(result);

                    SummaryView summaryView = new SummaryView(ticket.@return);
                    summaryView.ShowDialog();
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
        }
    }
}
