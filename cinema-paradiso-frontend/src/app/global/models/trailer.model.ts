export class Trailer {
  trailer_id: number;
  name: string;
  path: string;
  imdb_id: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
