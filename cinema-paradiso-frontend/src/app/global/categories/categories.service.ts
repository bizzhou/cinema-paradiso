import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class CategoriesService {

  constructor() { }

  private selectedTab: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  private currentTab: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  public setSelectedTab(tab: any): void {
    this.selectedTab.next(tab);
  }

  public getTabActive(tab: any) {
    return this.selectedTab === tab;
  }

  public getSelectedTab(): Observable<string> {
    return this.selectedTab;
  }

  public setCurrentTab(tab: any) {
    this.currentTab.next(tab);
  }

  public getCurrentTab() {
    return this.currentTab;
  }

}
