export class Trailer {
  trailerId: number;
  name: string;
  path: string;
  imdbId: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
