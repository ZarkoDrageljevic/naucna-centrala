export class DownloadFile {
    fileName: String

    constructor(downloadFile: DownloadFileInterface = {}) {
        this.fileName = downloadFile.fileName;
    }
    set setFileName(fileName: string) {
        this.fileName = fileName;
    }
}

interface DownloadFileInterface {
    fileName?: String
}