package ru.vpt.constructorapp.service.commercial;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;

import java.io.*;
import java.util.List;
import java.util.Objects;

import static org.apache.poi.ss.usermodel.CellType.*;

@Service
public class ReportService {

    private HSSFWorkbook workbook;
    private int cursor = 68;
    private final String SHEET_NAME = "КП";


    public ByteArrayInputStream report(CommercialPropEntity entity) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook = new HSSFWorkbook(Objects.requireNonNull(Model.class.getClassLoader().getResourceAsStream("reportTemplate.xls")));
            if (Objects.isNull(entity.getCommercialPropItems()) || entity.getCommercialPropItems().isEmpty())
                throw new BadRequestException("Невозможно сформировать отчет, отсутствуют элементы", 400);
            fillCommItems(entity.getCommercialPropItems());


            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillAdditionData(String commNumber, String partner, String date, ManagerEntity manager) {

    }

    private void fillCommItems(List<CommercialPropItemEntity> items) {
        for(CommercialPropItemEntity item : items){
            if(item.getProduct().getProductType().getIdProductType().equals(1L))
                fillMotor(item);
            if(item.getProduct().getProductType().getIdProductType().equals(2L))
                fillReducer(item);
            if(item.getProduct().getProductType().getIdProductType().equals(3L))
                fillMotorReducer(item);
        }
    }

    private void fillReducer(CommercialPropItemEntity item){

    }

    private void fillMotor(CommercialPropItemEntity item){
        int startRow = cursor;
        for (int i = 38; i < 55; i++) {
            copyRow(workbook, workbook.getSheet(SHEET_NAME), i, cursor);
            cursor++;
        }
        workbook.getSheet(SHEET_NAME).getRow(startRow).getCell(2)
                .setCellValue(item.getProduct().getName() == null ? " " : item.getProduct().getName());
        workbook.getSheet(SHEET_NAME).getRow(startRow + 4).getCell(3)
                .setCellValue(item.getProduct().getRpm() == null ? " " :  item.getProduct().getRpm().toString());
        workbook.getSheet(SHEET_NAME).getRow(startRow + 5).getCell(3)
                .setCellValue(item.getProduct().getMotor().getPower() == null ? " " :  item.getProduct().getMotor().getPower().toString());
        workbook.getSheet(SHEET_NAME).getRow(startRow + 6).getCell(3)
                .setCellValue(item.getProduct().getMotor().getEfficiency() == null ? " " :  item.getProduct().getMotor().getEfficiency().toString());
        workbook.getSheet(SHEET_NAME).getRow(startRow + 7).getCell(3)
                .setCellValue(item.getProduct().getMotor().getRatedCurrent() == null ? " " :  item.getProduct().getMotor().getRatedCurrent().toString());
//        workbook.getSheet(SHEET_NAME).getRow(startRow + 8).getCell(3)
//                .setCellValue(item.getProduct().getMotor().getPosTerminalBox() == null ? " " :  item.getProduct().getMotor().getPosTerminalBox().toString());
//        workbook.getSheet(SHEET_NAME).getRow(startRow + 8).getCell(3)
//                .setCellValue(item.getProduct().getMotor().getCableExitSide() == null ? " " :  item.getProduct().getMotor().getCableExitSide());
//        workbook.getSheet(SHEET_NAME).getRow(startRow + 9).getCell(3)
//                .setCellValue(item.getProduct().getOptions()== null ? " " :  item.getProduct().getMotor().getCableExitSide());
    }

    private void fillMotorReducer(CommercialPropItemEntity item){

    }

    private static void copyRow(HSSFWorkbook workbook, HSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
        HSSFRow newRow = worksheet.getRow(destinationRowNum);
        HSSFRow sourceRow = worksheet.getRow(sourceRowNum);

        // If the row exist in destination, push down all rows by 1 else create a new row
        if (newRow != null) {
            worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
        } else {
            newRow = worksheet.createRow(destinationRowNum);
        }

        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            HSSFCell oldCell = sourceRow.getCell(i);
            HSSFCell newCell = newRow.createCell(i);

            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }

            // Copy style from old cell and apply to new cell
            HSSFCellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            ;
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellType());

            // Set the cell data value
            switch (oldCell.getCellType()) {
                case BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
            }
        }

        // If there are are any merged regions in the source row, copy to new row
        for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
            if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(newRow.getRowNum(),
                        (newRow.getRowNum() +
                                (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow()
                                )),
                        cellRangeAddress.getFirstColumn(),
                        cellRangeAddress.getLastColumn());
                worksheet.addMergedRegion(newCellRangeAddress);
            }
        }
    }
}
