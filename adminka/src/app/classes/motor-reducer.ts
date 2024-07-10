import { DiameterOutputShaft, ReducerInstallationType, ReducerOutputShaftType, ReducerSize, ReducerType } from "./reducer"

export class MotorReducer {
    type: ReducerType
    size: ReducerSize
    diameterOutputShaft: DiameterOutputShaft
    outputShaftType: ReducerOutputShaftType
    motorPower: Number | null
    torqueMoment: Number | null
    iRatio: Number | null
    installationType: ReducerInstallationType
    mounting: Number | null
    power: Number | null

    constructor() {
        this.type = new ReducerType()
        this.size = new ReducerSize()
        this.diameterOutputShaft = new DiameterOutputShaft()
        this.outputShaftType = new ReducerOutputShaftType()
        this.motorPower = null
        this.torqueMoment = null
        this.iRatio = null
        this.installationType = new ReducerInstallationType()
        this.mounting = null
        this.power = null
    }
}