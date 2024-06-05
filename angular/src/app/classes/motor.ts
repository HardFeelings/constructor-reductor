import { HttpClient } from "@angular/common/http"

export class Motor {
    id: number
    type: MotorType
    adapterType: MotorAdapterType
    power: Number | null
    frequency: MotorFrequency
    rpm: MotorRPM

    constructor();
    constructor(http?: HttpClient, id?: number) {
        this.id = 0
        this.type = new MotorType()
        this.adapterType = new MotorAdapterType()
        this.power = null
        this.frequency = new MotorFrequency()
        this.rpm = new MotorRPM()
        if (http != undefined) {
            http.get(`/api/v1/motor/${id}`).subscribe({
                next: (data: any) => {
                    data.data.forEach((e: { [x: string]: any; }) => {
                        this.id = e["idMotor"]
                        this.frequency.value = e["frequency"]
                        this.adapterType.id = e["motorAdapterTypeId"]
                        this.power = e["power"]
                        this.rpm.value = e["rpm"]
                        this.type.id = e["motorTypeId"]
                    })
                },
                error: error => { console.log(error); }
            });
        }
    }


    ser(): any {
        return {
            idMotor: this.id,
            power: this.power,
            frequency: this.frequency.value,
            rpm: this.rpm.value,
            motorTypeId: this.type.id,
            motorAdapterTypeId: this.adapterType.id
        }
    }

    delete(http: HttpClient) {
        console.log(this)
        http.delete(`/api/v1/security/motor/${this.id}`)
            .subscribe({
                next: (data: any) => {
                    console.log(data.data)
                },
                error: error => {
                    console.log(error)
                }
            });
    }

    save(http: HttpClient) {
        console.log(this)
        http.post('/api/v1/security/motor', this.ser()
        ).subscribe({
            next: (data: any) => {
                console.log(data.data)
            },
            error: error => {
                console.log(error)
            }
        });
    }
}

export class MotorType {
    id: Number
    value: string | null
    variants: string[]

    constructor() {
        this.id = 0
        this.variants = ["общепромышленный", "двигатель стандарта DIN", "взрывозащищенный"]
        this.value = null
    }

    ser(): any {
        return {
            idMotorType: this.id,
            motorTypeName: this.value,
        }
    }

    save(http: HttpClient) {
        console.log(this)
        http.post('/api/v1/security/motorType', this.ser()
        ).subscribe({
            next: (data: any) => {
                console.log(data.data)
            },
            error: error => {
                console.log(error)
            }
        });
    }

    delete(http: HttpClient) {
        console.log(this)
        http.delete(`/api/v1/security/motorType/${this.id}`)
            .subscribe({
                next: (data: any) => {
                    console.log(data.data)
                },
                error: error => {
                    console.log(error)
                }
            });
    }
}

export class MotorAdapterType {
    id: Number
    typeid: Number
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["B5", "B14"]
        this.value = null
        this.typeid = 0
        this.id = 0
    }

    ser(): any {
        return {
            idMotorAdapterType: this.id,
            motorAdapterTypeValue: this.value,
            motorTypeId: this.typeid
        }
    }

    save(http: HttpClient) {
        console.log(this)
        http.post('/api/v1/security/motorAdapterType', this.ser()
        ).subscribe({
            next: (data: any) => {
                console.log(data.data)
            },
            error: error => {
                console.log(error)
            }
        });
    }

    delete(http: HttpClient) {
        console.log(this)
        http.delete(`/api/v1/security/motorAdapterType/${this.id}`)
            .subscribe({
                next: (data: any) => {
                    console.log(data.data)
                },
                error: error => {
                    console.log(error)
                }
            });
    }
}

export class MotorFrequency {
    value: number | null
    variants: number[]

    constructor() {
        this.variants = [50, 60]
        this.value = null
    }
}

export class MotorRPM {
    value: number | null
    variants: number[]

    constructor() {
        this.variants = [750, 1000, 1500, 3000]
        this.value = null
    }
}

