import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SimulationService } from './service/simulation.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],
  providers: [SimulationService],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  // Configuration values
  configuration ={
  totalTickets: null,
    ticketReleaseRate: null,
    customerRetrievalRate: null,
    maxTicketCapacity: null,}

  // Status counters
  activeVendors: number = 0;
  activeCustomers: number = 0;
  vipCustomers: number = 0;

  // Ticket pool
  availableTickets: number = 0;
  totalReleased: number = 0;

  // Logs and real-time data
  logs: string[] = [];
  ticketSales: { timestamp: string; sales: number }[] = [];

  // System state
  systemRunning: boolean = false;

  constructor(private simulationService: SimulationService) {}

  saveConfiguration() {
    this.simulationService.setConfiguration(this.configuration).subscribe({
      next: () => alert('Configuration saved successfully'),
      error: (err) => alert('Failed to save configuration: ' + err.message),
    });
  }

  startSimulation() {
    this.simulationService.startSimulation().subscribe({
      next: () => this.pollLogs(),
      error: (err) => alert('Failed to start simulation: ' + err.message),
    });
  }

  stopSimulation() {
    this.simulationService.stopSimulation().subscribe({
      next: () => alert('Simulation stopped'),
      error: (err) => alert('Failed to stop simulation: ' + err.message),
    });
  }

  pollLogs() {
    setInterval(() => {
      this.simulationService.getLogs().subscribe((data) => (this.logs = data));
    }, 1000);
  }

  incrementCounter(type: string) {
    if (type === 'vendors') this.activeVendors++;
    if (type === 'customers') this.activeCustomers++;
    if (type === 'vip') this.vipCustomers++;
  }

  decrementCounter(type: string) {
    if (type === 'vendors' && this.activeVendors > 0) this.activeVendors--;
    if (type === 'customers' && this.activeCustomers > 0) this.activeCustomers--;
    if (type === 'vip' && this.vipCustomers > 0) this.vipCustomers--;
  }
}
