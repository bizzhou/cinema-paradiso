import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class LoginStatusService {
	private logInStatus = new BehaviorSubject<boolean>(false);
	currentStatus = this.logInStatus.asObservable();
	constructor() {}

	changeStatus(status: boolean) {
		this.logInStatus.next(status);
	}

	getTokenDetails() {
		let user = JSON.parse(localStorage.getItem("credential"));
		return user;
	} 

}