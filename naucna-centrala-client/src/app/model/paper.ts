import {ScienceField} from './science-field';
import {CoAuthor} from './co-author';

export class Paper {
  title: string;
  keywords: string;
  paperAbstract: string;
  scienceField: ScienceField;
  coauthors: Array<CoAuthor>;

}
