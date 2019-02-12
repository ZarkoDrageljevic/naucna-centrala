interface UploadFile {
    keywords: String;
    magazine: String;
    scientificField: String;
    files?: any;
    apstrakt: String;
    authors: Array<Author>
}

interface Author{
    firstname: String;
    lastname: String;
}