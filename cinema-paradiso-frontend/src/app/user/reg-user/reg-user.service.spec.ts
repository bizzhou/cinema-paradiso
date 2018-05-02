import { TestBed, inject } from '@angular/core/testing';

import { RegUserService } from './reg-user.service';

describe('RegUserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RegUserService]
    });
  });

  it('should be created', inject([RegUserService], (service: RegUserService) => {
    expect(service).toBeTruthy();
  }));
});
