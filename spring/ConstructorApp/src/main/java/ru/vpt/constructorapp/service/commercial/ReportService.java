package ru.vpt.constructorapp.service.commercial;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.service.util.Tuple;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ReportService {

    private HSSFWorkbook workbook;
    private int cursor;
    private final String SHEET_NAME = "КП";
    private List<Tuple<Integer,Short>> heightList;


    public ByteArrayInputStream report(CommercialPropEntity entity) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook = new HSSFWorkbook(Objects.requireNonNull(Model.class.getClassLoader().getResourceAsStream("reportTemplate.xls")));
            if (Objects.isNull(entity.getCommercialPropItems()) || entity.getCommercialPropItems().isEmpty())
                throw new BadRequestException("Невозможно сформировать отчет, отсутствуют элементы", 400);
            cursor = 52;
            heightList = new ArrayList<>();
            fillHeader(entity.getManager(), entity.getNumber(), entity.getPartner(), entity.getTimestamp());
            fillCommItems(entity.getCommercialPropItems());
            clearTrash();
            setRowsHeight();

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillHeader(ManagerEntity manager, String number, String partner, String timestamp) {
        printCell(number, 10, 5);
        printCell(timestamp, 10, 7);
        if (!Objects.isNull(manager))
            printCell(manager.getShortName(), 8, 9);
        printCell(partner, 9, 9);
    }

    private void fillAdditionData(String commNumber, String partner, String date, ManagerEntity manager) {

    }

    private void fillCommItems(List<CommercialPropItemEntity> items) {
        int count = 1;
        for (CommercialPropItemEntity item : items) {
            if (item.getProduct().getProductType().getIdProductType().equals(1L))
                fillMotor(item, count);
            if (item.getProduct().getProductType().getIdProductType().equals(2L))
                fillReducer(item, count);
            if (item.getProduct().getProductType().getIdProductType().equals(3L))
                fillMotorReducer(item, count);
            count++;
        }
    }

    private void fillReducer(CommercialPropItemEntity item, int count) {
        int startRow = cursor;
        for (int i = 42; i < 52; i++) {
            copyRow(workbook, workbook.getSheet(SHEET_NAME), i, cursor);
            cursor++;
        }
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, startRow, 2, 3));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor-1, 1, 1));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor-1, 4, 6));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor-1, 7, 7));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor-1, 8, 9));
        heightList.add(new Tuple<>(startRow - 38, (short) 960));
        printCell(String.valueOf(count), startRow, 1);

        if (!Objects.isNull(item.getProduct())) {
            printCell(item.getProduct().getName(), startRow, 2);
            printCell(item.getProduct().getReducer().getReducerType().getReducerTypeName(), startRow + 2, 3);
            printCell(String.valueOf(item.getProduct().getReducer().getRatio()), startRow + 3, 3);
            printCell(item.getProduct().getReducer().getReducerMounting().getReducerMountingValue(), startRow + 4, 3);
            printCell(item.getProduct().getReducer().getReducerInstallationType()
                    .getReducerInstallationTypeValue().toLowerCase().contains("фланц") ? "Да" : "Нет", startRow + 5, 3);
            printCell(item.getProduct().getReducer().getReducerOutputShaftType().getReducerOutputShaftTypeValue(), startRow + 7, 3);
            printCell("Ø" + item.getProduct().getReducer().getDiameterOutputShaft(), startRow + 8, 3);
            printCell(String.valueOf(item.getProduct().getWeight()), startRow + 9, 3);
        }
        printCell(String.valueOf(item.getAmount()), startRow, 4);
        printCell(String.valueOf(item.getProduct().getPrice()), startRow, 7);
        printCell(String.valueOf(item.getProduct().getPrice() * item.getAmount()), startRow, 8);
        cursor++;

    }

    private void fillMotor(CommercialPropItemEntity item, int count) {
        int startRow = cursor;
        for (int i = 31; i < 41; i++) {
            copyRow(workbook, workbook.getSheet(SHEET_NAME), i, cursor);
            cursor++;
        }
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, startRow, 2, 3));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor, 1, 1));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor, 4, 6));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor, 7, 7));
        workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor, 8, 9));
        heightList.add(new Tuple<>(startRow - 38, (short) 960));
        printCell(String.valueOf(count), startRow, 1);
        if (!Objects.isNull(item.getProduct())) {
            printCell(item.getProduct().getName(), startRow, 2);
            printCell(String.valueOf(item.getProduct().getRpm()), startRow + 2, 3);
            if (!Objects.isNull(item.getProduct().getMotor())) {
                printCell(String.valueOf(item.getProduct().getMotor().getPower()), startRow + 3, 3);
                printCell(String.valueOf(item.getProduct().getMotor().getEfficiency()), startRow + 4, 3);
                printCell(String.valueOf(item.getProduct().getMotor().getRatedCurrent()), startRow + 5, 3);
                printCell(String.valueOf(item.getProduct().getMotor().getMomentOfInertia()), startRow + 8, 3);
            }
            printCell(String.valueOf(item.getProduct().getWeight()), startRow + 9, 3);
            if (!Objects.isNull(item.getProduct().getOptions()) && !item.getProduct().getOptions().isEmpty()) {
                StringBuilder optionSB = new StringBuilder("Доп. Опции: ");
                short height = (short) (workbook.getSheet(SHEET_NAME).getRow(startRow + 10).getHeight() + 150);
                for (int i = 0; i < item.getProduct().getOptions().size(); i++) {
                    if (i == item.getProduct().getOptions().size() - 1)
                        optionSB.append(item.getProduct().getOptions().stream().toList().get(i).getProductOptionValue()).append(".");
                    else
                        optionSB.append(item.getProduct().getOptions().stream().toList().get(i).getProductOptionValue()).append(",").append("\n");
                }

                height *= item.getProduct().getOptions().size();
                printCell(optionSB.toString(), startRow + 10, 2);
                CellStyle wrapStyle = workbook.createCellStyle();
                wrapStyle.setWrapText(true);
                heightList.add(new Tuple<>((startRow - 38) + 10, height));
                workbook.getSheet(SHEET_NAME).getRow(startRow + 10).getCell(2).setCellStyle(wrapStyle);
                workbook.getSheet(SHEET_NAME).getRow(startRow + 10).setHeight(height);

            } else
                workbook.getSheet(SHEET_NAME).removeRow(workbook.getSheet(SHEET_NAME).getRow(startRow + 10));
            printCell(String.valueOf(item.getAmount()), startRow, 4);
            printCell(String.valueOf(item.getProduct().getPrice()), startRow, 7);
            printCell(String.valueOf(item.getProduct().getPrice() * item.getAmount()), startRow, 8);

            cursor++;
        }
    }

    private void setRowsHeight(){
        for(Tuple<Integer,Short> item : heightList){
            workbook.getSheet(SHEET_NAME).getRow(item.getX()).setHeight(item.getY());
        }
    }
    private void fillMotorReducer(CommercialPropItemEntity item, int count) {

    }

    private void clearTrash() {
        for (int i = 14; i < 52; i++) {
            System.out.println(i);
            deleteRow(workbook.getSheet(SHEET_NAME), workbook.getSheet(SHEET_NAME).getRow(14));
        }
    }

    public static void deleteRow(HSSFSheet sheet, HSSFRow row) {
        sheet.removeRow(row);
        int rowIndex = row.getRowNum();
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
    }

    private void printCell(String value, int rowNumber, int cellNumber) {
        if (value == null)
            value = "";
        workbook.getSheet(SHEET_NAME).getRow(rowNumber).getCell(cellNumber).setCellValue(value);
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
