import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Tema } from '../model/Tema';

@Injectable({
  providedIn: 'root'
})
export class TemaService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }


  getAllTema(): Observable<Tema[]>{
    return this.http.get<Tema[]>("https://feiracertsys.herokuapp.com/tema") // - API do professor//--> Inserir: , this.token --> Após config na basesecurity api

  }

  getByIdTema(id: number): Observable<Tema>{
    return this.http.get<Tema>(`https://feiracertsys.herokuapp.com/tema/${id}`)

  }

  getByNomeTema(nome: string): Observable<Tema[]>{
    return this.http.get<Tema[]>(`https://feiracertsys.herokuapp.com/tema/nome/${nome}`)

  }

  postTema(tema: Tema): Observable<Tema>{
    return this.http.post<Tema>("https://feiracertsys.herokuapp.com/tema", tema) // - API do professor //--> Inserir: , this.token --> Após config na basesecurity api

  }

  putTema(tema: Tema): Observable<Tema>{
    return this.http.put<Tema>("https://feiracertsys.herokuapp.com/tema", tema)
  }

  deleteTema(id: number){
    return this.http.delete(`https://feiracertsys.herokuapp.com/tema/${id}`)
  }



}
