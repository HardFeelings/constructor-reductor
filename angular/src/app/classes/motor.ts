export class Motor {
    type: MotorType
    adapterType: MotorAdapterType
    power: Number | null
    frequency: MotorFrequency
    rpm: MotorRPM

    constructor() {
        this.type = new MotorType()
        this.adapterType = new MotorAdapterType()
        this.power = null
        this.frequency = new MotorFrequency()
        this.rpm = new MotorRPM()
    }
}

export class MotorType {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["общепромышленный", "двигатель стандарта DIN", "взрывозащищенный"]
        this.value = null
    }
}

export class MotorAdapterType {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["B5", "B14"]
        this.value = null
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
