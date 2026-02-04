import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SimulationService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  setConfiguration(config: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/configuration`, config);
  }

  startSimulation(): Observable<any> {
    return this.http.post(`${this.baseUrl}/simulation/start`, {});
  }

  stopSimulation(): Observable<any> {
    return this.http.post(`${this.baseUrl}/simulation/stop`, {});
  }

  getLogs(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/logs`);
  }
}
