import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NavegacionComponent } from './components/navegacion/navegacion.component';
import { TablaComponent } from './components/tabla/tabla.component';
import { ComponenteDePruebaComponent } from './components/componente-de-prueba/componente-de-prueba.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'navegacion', component: NavegacionComponent },
  { path: 'tabla', component: TablaComponent },
  { path: 'prueba', component: ComponenteDePruebaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }