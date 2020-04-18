import { Component, OnInit, } from '@angular/core';
import * as L from 'leaflet';
@Component({
  selector: 'app-addmap',
  templateUrl: './addmap.component.html',
  styleUrls: ['./addmap.component.scss']
})
export class AddmapComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    var map = L.map('map', { center: [34, 8], zoom: 7 }); 
    var url = 'http://localhost:8080/geoserver/webmapespace/wms';
     L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(map);
     L.tileLayer.betterWms(url, { layers: 'TUN_adm1', transparent: true, format: 'image/png' }).addTo(map);
  }

}
