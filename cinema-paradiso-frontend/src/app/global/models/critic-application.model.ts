export class CriticApplication {
  reason: string;
  id: number;
  email: string;
  username: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
