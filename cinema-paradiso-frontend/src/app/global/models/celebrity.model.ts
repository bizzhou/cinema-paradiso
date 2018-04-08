export class Celebrity {
  celebrityId: string;
  photoLocation: string;
  biography: string;
  birthCity: string;
  birthCountry: string;
  birthDate: Date;
  birthState: string;
  isDirector: boolean;
  name: string;
  profileImage: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
