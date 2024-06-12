import { HttpClient } from "@angular/common/http"
import { Observable } from "rxjs"

export class Reducer {
    idReducer: Number
    reducerTypeId: Number
    type: ReducerType
    reducerSizeId: Number
    size: ReducerSize
    diameterInputShaft: Number
    diameterOutputShaft: Number
    reducerInputTypeId: Number
    inputType: ReducerInputType
    reducerAdapterTypeId: Number
    adapterType: ReducerAdapterType
    reducerOutputShaftTypeId: Number
    outputShaftType: ReducerOutputShaftType
    reducerInstallationTypeId: Number
    installationType: ReducerInstallationType
    reducerMountingId: Number
    mounting: MountingPoint

    torqueMoment: Number
    ratio: Number



    constructor() {
        this.type = new ReducerType()
        this.size = new ReducerSize()
        this.diameterInputShaft = 0
        this.diameterOutputShaft = 0
        this.inputType = new ReducerInputType()
        this.adapterType = new ReducerAdapterType()
        this.outputShaftType = new ReducerOutputShaftType()
        this.installationType = new ReducerInstallationType()
        this.mounting = new MountingPoint()
        this.idReducer = 0
        this.reducerTypeId = 0
        this.reducerSizeId = 0
        this.reducerInputTypeId = 0
        this.reducerOutputShaftTypeId = 0
        this.reducerAdapterTypeId = 0
        this.reducerInstallationTypeId = 0
        this.reducerMountingId = 0
        this.torqueMoment = 0
        this.ratio = 0
    }

    ser(): any {
        return {
            idReducer: this.idReducer,
            reducerTypeId: this.reducerTypeId,
            reducerSizeId: this.reducerSizeId,
            reducerInputTypeId: this.reducerInputTypeId,
            reducerAdapterTypeId: this.reducerAdapterTypeId,
            reducerOutputShaftTypeId: this.reducerOutputShaftTypeId,
            reducerInstallationTypeId: this.reducerInstallationTypeId,
            reducerMountingId: this.reducerMountingId,
            diameterInputShaft: this.diameterInputShaft,
            diameterOutputShaft: this.diameterOutputShaft,
            ratio: this.ratio,
            torqueMoment: this.torqueMoment
        }
    }

    public static getAll(http: HttpClient): Array<Reducer> {
        var list: Reducer[] = new Array<Reducer>()
        http.get('/api/v1/reducer')
            .subscribe({
                next: (data: any) => {
                    console.log("get all")
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var reducer = new Reducer()
                        reducer.idReducer = e["idReducer"]
                        reducer.reducerTypeId = e["reducerTypeId"]
                        reducer.reducerSizeId = e["reducerSizeId"]
                        reducer.reducerInputTypeId = e["reducerInputTypeId"]
                        reducer.reducerAdapterTypeId = e["reducerAdapterTypeId"]
                        reducer.reducerOutputShaftTypeId = e["reducerOutputShaftTypeId"]
                        reducer.reducerInstallationTypeId = e["reducerInstallationTypeId"]
                        reducer.reducerMountingId = e["reducerMountingId"]
                        reducer.diameterInputShaft = e["diameterInputShaft"]
                        reducer.diameterOutputShaft = e["diameterOutputShaft"]
                        reducer.ratio = e["ratio"]
                        reducer.torqueMoment = e["torqueMoment"]
                        list.push(reducer)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean>{
       return http.delete<boolean>(`/api/v1/security/reducer/${this.idReducer}`)
    }

    save(http: HttpClient): Observable<Reducer> {
        return http.post<Reducer>('/api/v1/security/reducer', this.ser())
    }
}

export class ReducerType {
    idReducerType: number
    reducerTypeName: string | null
    variants: string[]

    constructor() {
        this.variants = ["червячный", "соосно-цилиндрический", "плоскоцилиндрический", "конический"]
        this.reducerTypeName = null
        this.idReducerType = 0
    }


    ser(): any {
        return {
            idReducerType: this.idReducerType,
            reducerTypeName: this.reducerTypeName
        }
    }

    public static getAll(http: HttpClient): Array<ReducerType> {
        var list: ReducerType[] = new Array<ReducerType>()
        http.get('/api/v1/reducerType')
            .subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var reducerType = new ReducerType()
                        reducerType.idReducerType = e["idReducerType"]
                        reducerType.reducerTypeName = e["reducerTypeName"]
                        list.push(reducerType)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean> {
      return  http.delete<boolean>(`/api/v1/security/reducerType/${this.idReducerType}`)
    }

    save(http: HttpClient): Observable<ReducerType> {
        return http.post<ReducerType>('/api/v1/security/reducerType', this.ser())
    }
}

export class ReducerSize {
    idReducerSize: number
    reducerSizeValue: number | null
    variants: number[]
    reducerTypeId: number

    constructor() {
        this.variants = [100, 50]
        this.reducerSizeValue = null
        this.idReducerSize = 0
        this.reducerTypeId = 0
    }

    ser(): any {
        return {
            idReducerSize: this.idReducerSize,
            reducerSizeValue: this.reducerSizeValue,
            reducerTypeId: this.reducerTypeId
        }
    }

    public static getAll(http: HttpClient): Array<ReducerSize> {
        var list: ReducerSize[] = new Array<ReducerSize>()
        http.get('/api/v1/reducerSize')
            .subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var reducerSize = new ReducerSize()
                        reducerSize.idReducerSize = e["idReducerSize"]
                        reducerSize.reducerSizeValue = e["reducerSizeValue"]
                        reducerSize.reducerTypeId = e["reducerTypeId"]
                        list.push(reducerSize)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean>{
        return http.delete<boolean>(`/api/v1/security/reducerSize/${this.idReducerSize}`)
    }

    save(http: HttpClient): Observable<ReducerSize> {
        return http.post<ReducerSize>('/api/v1/security/reducerSize', this.ser())
    }
}

export class DiameterInputShaft {
    value: number | null
    eps: number | null

    constructor() {
        this.value = null
        this.eps = null
    }
}

export class DiameterOutputShaft {
    value: number | null
    eps: number | null

    constructor() {
        this.value = null
        this.eps = null
    }
}

export class ReducerInputType {
    idReducerInputType: number
    value: string | null
    variants: string[]
    reducerTypeId: number

    constructor() {
        this.variants = ["Адаптер IEC", "Сплошной вал."]
        this.value = null
        this.idReducerInputType = 0
        this.reducerTypeId = 0
    }

    ser(): any {
        return {
            idReducerInputType: this.idReducerInputType,
            reducerInputTypeValue: this.value,
            reducerTypeId: this.reducerTypeId
        }
    }

    public static getAll(http: HttpClient): Array<ReducerInputType> {
        var list: ReducerInputType[] = new Array<ReducerInputType>()
        http.get('/api/v1/reducerInputType')
            .subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var reducerInputType = new ReducerInputType()
                        reducerInputType.idReducerInputType = e["idReducerInputType"]
                        reducerInputType.value = e["reducerInputTypeValue"]
                        reducerInputType.reducerTypeId = e["reducerTypeId"]
                        list.push(reducerInputType)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean> {
        return http.delete<boolean>(`/api/v1/security/reducerInputType/${this.idReducerInputType}`)
    }

    save(http: HttpClient) : Observable<ReducerInputType> {
        return http.post<ReducerInputType>('/api/v1/security/reducerInputType', this.ser())
    }
}

export class ReducerAdapterType {
    idReducerAdapterType: number
    value: string | null
    variants: string[]
    reducerTypeId: number

    constructor() {
        this.variants = ["В5", "В14"]
        this.value = null
        this.idReducerAdapterType = 0
        this.reducerTypeId = 0
    }

    ser(): any {
        return {
            idReducerAdapterType: this.idReducerAdapterType,
            reducerAdapterTypeValue: this.value,
            reducerTypeId: this.reducerTypeId
        }
    }

    public static getAll(http: HttpClient): Array<ReducerAdapterType> {
        var list: ReducerAdapterType[] = new Array<ReducerAdapterType>()
        http.get('/api/v1/reducerAdapterType')
            .subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var reducerAdapterType = new ReducerAdapterType()
                        reducerAdapterType.idReducerAdapterType = e["idReducerAdapterType"]
                        reducerAdapterType.value = e["reducerAdapterTypeValue"]
                        reducerAdapterType.reducerTypeId = e["reducerTypeId"]
                        list.push(reducerAdapterType)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean>{
        return http.delete<boolean>(`/api/v1/security/reducerAdapterType/${this.idReducerAdapterType}`)
    }

    save(http: HttpClient): Observable<ReducerAdapterType> {
        return http.post<ReducerAdapterType>('/api/v1/security/reducerAdapterType', this.ser())
    }
}

export class ReducerOutputShaftType {
    idReducerOutputShaftType: number
    value: string | null
    variants: string[]
    reducerTypeId: number


    constructor() {
        this.variants = ["полый", "цельный вал", "стяжная муфта"]
        this.value = null
        this.idReducerOutputShaftType = 0
        this.reducerTypeId = 0
    }

    ser(): any {
        return {
            idReducerOutputShaftType: this.idReducerOutputShaftType,
            reducerOutputShaftTypeValue: this.value,
            reducerTypeId: this.reducerTypeId
        }
    }

    public static getAll(http: HttpClient): Array<ReducerOutputShaftType> {
        var list: ReducerOutputShaftType[] = new Array<ReducerOutputShaftType>()
        http.get('/api/v1/reducerOutputShaftType')
            .subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var reducerOutputShaftType = new ReducerOutputShaftType()
                        reducerOutputShaftType.idReducerOutputShaftType = e["idReducerOutputShaftType"]
                        reducerOutputShaftType.value = e["reducerOutputShaftTypeValue"]
                        reducerOutputShaftType.reducerTypeId = e["reducerTypeId"]
                        list.push(reducerOutputShaftType)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean>{
      return  http.delete<boolean>(`/api/v1/security/reducerOutputShaftType/${this.idReducerOutputShaftType}`)
    }

    save(http: HttpClient): Observable<ReducerOutputShaftType> {
        return http.post<ReducerOutputShaftType>('/api/v1/security/reducerOutputShaftType', this.ser())
    }
}

export class ReducerInstallationType {
    idReducerInstallationType: number
    value: string | null
    variants: string[]
    reducerTypeId: number

    constructor() {
        this.variants = ["на лапах", "на фланце", "моментный рычаг"]
        this.value = null
        this.idReducerInstallationType = 0
        this.reducerTypeId = 0
    }

    ser(): any {
        return {
            idReducerInstallationType: this.idReducerInstallationType,
            reducerInstallationTypeValue: this.value,
            reducerTypeId: this.reducerTypeId

        }
    }

    public static getAll(http: HttpClient): Array<ReducerInstallationType> {
        var list: ReducerInstallationType[] = new Array<ReducerInstallationType>()
        http.get('/api/v1/reducerInstallationType')
            .subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var reducerInstallationType = new ReducerInstallationType()
                        reducerInstallationType.idReducerInstallationType = e["idReducerInstallationType"]
                        reducerInstallationType.value = e["reducerInstallationTypeValue"]
                        reducerInstallationType.reducerTypeId = e["reducerTypeId"]
                        list.push(reducerInstallationType)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean>{
        return http.delete<boolean>(`/api/v1/security/reducerInstallationType/${this.idReducerInstallationType}`)
    }

    save(http: HttpClient):Observable<ReducerInstallationType> {
        return http.post<ReducerInstallationType>('/api/v1/security/reducerInstallationType', this.ser())
    }
}

export class MountingPoint {
    idReducerMounting: number
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["m1", "m2", "m3"]
        this.value = null
        this.idReducerMounting = 0
    }

    ser(): any {
        return {
            idReducerMounting: this.idReducerMounting,
            reducerMountingValue: this.value
        }
    }

    public static getAll(http: HttpClient): Array<MountingPoint> {
        var list: MountingPoint[] = new Array<MountingPoint>()
        http.get('/api/v1/reducerMounting')
            .subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        var mountingPoint = new MountingPoint()
                        mountingPoint.idReducerMounting = e["idReducerMounting"]
                        mountingPoint.value = e["reducerMountingValue"]
                        list.push(mountingPoint)
                    })
                },
                error: error => {
                    console.log(error)
                }
            });
        return list;
    }

    delete(http: HttpClient) : Observable<boolean>{
      return  http.delete<boolean>(`/api/v1/security/reducerMounting/${this.idReducerMounting}`)
    }

    save(http: HttpClient): Observable<MountingPoint> {
        return http.post<MountingPoint>('/api/v1/security/reducerMounting', this.ser())
    }
}
