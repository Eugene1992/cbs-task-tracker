import {Injectable} from '@angular/core';

import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  webSocketEndPoint: string = 'https://cbs-task-tracker.herokuapp.com/ws';
  stompClient: any;

  _subscribe(topic:string, success:any) {
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);

    this.stompClient.connect({}, () => {
      this.stompClient.subscribe(topic, success);
      //_this.stompClient.reconnect_delay = 2000;
    });
  }

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
  }

  /**
   * Send message to sever via web socket
   * @param {*} message
   */
  _send(message: any) {
    this.stompClient.send("/app/hello", {}, JSON.stringify(message));
  }

  /*onMessageReceived(message: any) {
    console.log("Message Recieved from Server :: " + message);
    this.appComponent.handleMessage(JSON.stringify(message.body));
  }*/
}
