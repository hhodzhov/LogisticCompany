import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';

@Component({
  selector: 'references',
  templateUrl: './references.component.html',
  styleUrls: ['./references.component.css'],
})
export class ReferencesComponent implements OnInit {
  constructor(private apiService: ApiService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.url.subscribe((url: any) => {
      this.selectedUsername = null;
      this.selectedSender = null;
      this.selectedRecipient = null;
      this.from = null;
      this.to = null;
      this.profit = null;
      this.refId = url[1].path;
      console.log(this.refId);
      this.loadData(this.refId);
    });
  }

  allShipments: any;

  refId: any;
  staff: any;
  clients: any;
  regShipments: any;

  usernames: any;
  selectedUsername: any;
  shipmentsBy: any;

  shipmentsNotReceived: any;

  allSenders: any;
  selectedSender: any;
  filtratedShipmentsBySender: any;

  allRecipients: any;
  selectedRecipient: any;
  filtratedShipmentsByRecipient: any;

  from: any;
  to: any;
  profit: any;

  loadData(refId: any) {
    this.loadAllShipments();
    if (refId === 'staff') {
      this.loadStaff();
    }
    if (refId === 'clients') {
      this.loadClients();
    }
    if (refId === 'ship-reg') {
      this.loadRegisteredShipments();
    }
    if (refId === 'ship-by') {
      this.loadShipmentsBy();
    }
    if (refId === 'ship-not-rec') {
      this.loadShipmentsNotReceived();
    }
  }

  loadStaff() {
    this.apiService.loadUsers().subscribe((data: any) => {
      let tmp = [];
      for (let user of data) {
        if (
          this.hasRole('ADMIN', user) ||
          this.hasRole('AGENT', user) ||
          this.hasRole('COURIER', user)
        ) {
          tmp.push(user);
        }
      }
      this.staff = tmp;
    });
  }

  loadClients() {
    this.apiService.loadUsers().subscribe((data: any) => {
      let tmp = [];
      for (let user of data) {
        if (this.hasRole('CLIENT', user)) {
          tmp.push(user);
        }
      }
      this.clients = tmp;
    });
  }

  loadRegisteredShipments() {
    this.apiService.loadShipments().subscribe((data: any) => {
      this.regShipments = data;
    });
  }

  loadShipmentsRegisteredBy() {
    this.apiService.loadShipments().subscribe((data: any) => {
      let tmp = [];
      for (let s of data) {
        if (s.agent == this.selectedUsername) {
          tmp.push(s);
        }
      }
      this.shipmentsBy = tmp;
    });
  }

  loadShipmentsNotReceived() {
    this.apiService.loadShipments().subscribe((data: any) => {
      let tmp = [];
      for (let s of data) {
        if (s.shipmentStatus !== 'DELIVERED') {
          tmp.push(s);
        }
      }
      this.shipmentsNotReceived = tmp;
    });
  }

  loadShipmentsBy() {
    this.loadUsers();
  }

  loadUsers() {
    this.apiService.loadUsers().subscribe((data: any) => {
      let tmp = [];
      for (let user of data) {
        tmp.push(user.username);
      }
      this.usernames = tmp;
    });
  }

  loadAllShipments() {
    this.apiService.loadShipments().subscribe((data: any) => {
      this.allShipments = data;
      let tmpSenders: any = [];
      let tmpRecipients: any = [];
      for (let s of this.allShipments) {
        if (!tmpSenders.includes(s.senderName)) {
          tmpSenders.push(s.senderName);
        }

        if (!tmpRecipients.includes(s.recipientName)) {
          tmpRecipients.push(s.recipientName);
        }
      }
      this.allSenders = tmpSenders;
      this.allRecipients = tmpRecipients;
    });
  }

  loadFiltratedShipmentsBySender() {
    let tmp: any = [];
    for (let s of this.allShipments) {
      if (s.senderName === this.selectedSender) {
        tmp.push(s);
      }
    }
    this.filtratedShipmentsBySender = tmp;
  }

  loadFiltratedShipmentsByRecipient() {
    let tmp: any = [];
    for (let s of this.allShipments) {
      if (s.recipientName === this.selectedRecipient) {
        tmp.push(s);
      }
    }
    this.filtratedShipmentsByRecipient = tmp;
  }

  loadProfit() {
    this.apiService
      .getProfit(this.from, this.to)
      .subscribe((data: any) => (this.profit = data));
  }

  private hasRole(roleName: any, user: any): boolean {
    for (let role of user.roles) {
      if (roleName === role.name) {
        return true;
      }
    }
    return false;
  }
}
