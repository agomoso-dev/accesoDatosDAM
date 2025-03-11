import { Component, ViewChild } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule]
})
export class TablaComponent {
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = [
    {position: 1, name: 'Churrasco', weight: 2, symbol: 'Churra'},
  
  ];

