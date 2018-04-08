export class Celebrity {
  celebrity_id: string;
  photo_location: string;
  biography: string;
  birth_city: string;
  birth_country: string;
  birth_date: Date;
  birth_state: string;
  is_director: boolean;
  name: string;
  profile_image: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
