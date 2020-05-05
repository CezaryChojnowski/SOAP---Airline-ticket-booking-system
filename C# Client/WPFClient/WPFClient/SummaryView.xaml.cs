using System;
using System.Collections.Generic;
using System.IO;
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
using WPFClient.TicketBookingServiceReference;

namespace WPFClient
{
    /// <summary>
    /// Interaction logic for SummaryView.xaml
    /// </summary>
    public partial class SummaryView : Window
    {
        public ticket ticket;

        public SummaryView()
        {
            InitializeComponent();
        }

        public SummaryView(ticket ticket)
        {
            InitializeComponent();
            InitFields(ticket);
            this.ticket = ticket;
        }

        private void InitFields(ticket ticket)
        {
            try
            {
                this.code.Content = ticket.code.ToString();
                this.date.Content = ticket.flight.flightDate;
                this.price.Content = ticket.flight.price;
                this.passenger.Content = $"{ticket.passenger.name} {ticket.passenger.surname}";
                this.email.Content = ticket.passenger.email;
                this.fromAirPort.Content = ticket.flight.from_AirPort.name;
                this.fromAddress.Content = $"{ticket.flight.from_AirPort.street} {ticket.flight.from_AirPort.numer_of_building}, {ticket.flight.from_AirPort.city}, {ticket.flight.from_AirPort.country}";
                this.toAirPort.Content = ticket.flight.to_AirPort.name;
                this.toAddress.Content = $"{ticket.flight.to_AirPort.street} {ticket.flight.to_AirPort.numer_of_building}, {ticket.flight.to_AirPort.city}, {ticket.flight.to_AirPort.country}";
            }
            catch (Exception)
            {

            }
        }

        private void SaveTicket_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                DataHelper.client.printTicketToPdf(this.ticket);
                MessageBox.Show("Pomyślnie zapisano bilet!", "Sukces", MessageBoxButton.OK, MessageBoxImage.Information);
                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}
