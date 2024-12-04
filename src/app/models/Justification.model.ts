export interface JustificationDTO {
    id?: number;
    type: JustificationType;
    dateInit: Date;
    dateEnd: Date;
    description: string;
  }
  
  export enum JustificationType {
    ESPORADICA = 'ESPORADICA',
    PERMANENTE = 'PERMANENTE'
  }
  