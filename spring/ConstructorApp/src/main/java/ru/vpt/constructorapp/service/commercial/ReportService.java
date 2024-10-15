package ru.vpt.constructorapp.service.commercial;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropTermsEntity;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


@Service
public class ReportService {

    private XSSFWorkbook workbook;
    private int cursor;
    private final String SHEET_NAME = "КП";
    private int endRow = 0;
    private double totalCost = 0;
    private double totalWeight = 0;
    private final String[] TERMS_PREFIXES = new String[]{
            "Первый платеж", "Второй платеж", "Третий платеж", "Четвертый платеж", "Пятый платеж", "Шестой платеж", "Седьмой платеж", "Восьмой платеж", "Девятый платеж", "Десятый платеж"
    };


    public ByteArrayInputStream report(CommercialPropEntity entity) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook = new XSSFWorkbook(Objects.requireNonNull(Model.class.getClassLoader().getResourceAsStream("reportTemplate.xlsx")));
            if (Objects.isNull(entity.getCommercialPropItems()) || entity.getCommercialPropItems().isEmpty())
                throw new BadRequestException("Невозможно сформировать отчет, отсутствуют элементы", 400);
            cursor = 85;
            fillHeader(entity.getManager(), entity.getNumber(), entity.getPartner(), entity.getTimestamp());
            fillCommItems(entity.getCommercialPropItems());
            fillAdditionData(entity);
            deleteRow(workbook.getSheet(SHEET_NAME), 13, 84);
            setPrintArea();
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillHeader(ManagerEntity manager, String number, String partner, String timestamp) {
        printCell(number, 9, 5);
        printCell(timestamp, 9, 7);
        if (!Objects.isNull(manager))
            printCell(manager.getShortName(), 7, 9);
        printCell(partner, 8, 9);
    }

    private void setPrintArea() {
        workbook.setPrintArea(
                0,
                0,
                10,
                0,
                workbook.getSheet(SHEET_NAME).getLastRowNum()
        );
        workbook.getSheet(SHEET_NAME).getPrintSetup().setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);

    }

    private void fillAdditionData(CommercialPropEntity entity) {
        int startRow = cursor;
        copyRows(13, 40, cursor, workbook.getSheet(SHEET_NAME));
        cursor += 29;
        printCell(String.valueOf(totalWeight), startRow, 8);
        printCell(formatMoney(totalCost), startRow + 1, 8);
        printCell(formatMoney((totalCost / 100) * 20), startRow + 2, 8);
        printAllTerms(entity.getCommercialPropTerms(), startRow);
        int termsRowsNum = entity.getCommercialPropTerms().size();
        if (entity.getDeliveryTime() == 90 || entity.getDeliveryTime() == 100)
            printCell("2. Срок поставки –  " + entity.getDeliveryTime() + "-та рабочих дней с момента оплаты первого платежа.",
                    startRow + 7 + termsRowsNum + 1, 1);
        else if (entity.getDeliveryTime() == 40)
            printCell("2. Срок поставки –  " + entity.getDeliveryTime() + "-ка рабочих дней с момента оплаты первого платежа.",
                    startRow + 7 + termsRowsNum + 1, 1);
        else if (findLastDigits(entity.getDeliveryTime()) == 1 && entity.getDeliveryTime() != 11 && entity.getDeliveryTime() != 111)
            printCell("2. Срок поставки –  " + entity.getDeliveryTime() + "-го рабочих дней с момента оплаты первого платежа.",
                    startRow + 7 + termsRowsNum + 1, 1);
        else if (findLastDigits(entity.getDeliveryTime()) == 2 || findLastDigits(entity.getDeliveryTime()) == 3 || findLastDigits(entity.getDeliveryTime()) == 4)
            printCell("2. Срок поставки –  " + entity.getDeliveryTime() + "-х рабочих дней с момента оплаты первого платежа.",
                    startRow + 7 + termsRowsNum + 1, 1);
        else
            printCell("2. Срок поставки –  " + entity.getDeliveryTime() + "-ти рабочих дней с момента оплаты первого платежа.",
                    startRow + 7 + termsRowsNum + 1, 1);
        printCell("3. Условия доставки: " + entity.getDeliveryTerms(), startRow + 7 + termsRowsNum + 2, 1);
        printCell("4. Оплата осуществляется с учетом НДС 20%.", startRow + 7 + termsRowsNum + 3, 1);
        printCell("5. Гарантия " + entity.getGuarantee() + " месяца.", startRow + 7 + termsRowsNum + 4, 1);
        printCell(entity.getManager().getPosition(), startRow + 7 + termsRowsNum + 11, 1);
        printCell(entity.getManager().getFullName(), startRow + 7 + termsRowsNum + 12, 1);
        printCell("Конт. Тел.: " + entity.getManager().getPhoneNumber(), startRow + 7 + termsRowsNum + 13, 1);
        printCell("email: " + entity.getManager().getEmail(), startRow + 7 + termsRowsNum + 14, 1);
    }

    private void printAllTerms(List<CommercialPropTermsEntity> terms, int startRow) {
        if (terms.size() != 1) {
            for (int i = 0; i < terms.size(); i++) {
                if (i != terms.size() - 1) {
                    insertRow(workbook.getSheet(SHEET_NAME), startRow + 7 + i + 1);
                    copyRows(startRow + 7, startRow + 7, startRow + 7 + i + 1, workbook.getSheet(SHEET_NAME));
                    cursor++;
                }
                printCell("    " + collectTermsString(terms.get(i), false), startRow + 7 + i, 1);
            }
        } else {
            printCell("    " + collectTermsString(terms.get(0), true), startRow + 7, 1);
            copyRows(startRow + 7, startRow + 7, startRow + 7 + 1, workbook.getSheet(SHEET_NAME));
            cursor++;
        }
        printCell("    1." + (terms.size() + 1) + " Оплата производится в российских рублях по курсу ЦБ РФ на дату проведения оплаты.", startRow + 7 + terms.size(), 1);


    }

    private String collectTermsString(CommercialPropTermsEntity terms, boolean isOne) {
        String termsString = terms.getPaymentTerms().getFullName();
        termsString = "1." + terms.getOrd() + " " + termsString;
        if (!isOne) {
            if (terms.getOrd() > TERMS_PREFIXES.length)
                termsString = termsString.replaceAll("<ord>", "");
            else
                termsString = termsString.replaceAll("<ord>", TERMS_PREFIXES[terms.getOrd() - 1]);
        } else {
            termsString = termsString.replaceAll("<ord>", "Платёж");
        }

        termsString = termsString.replaceAll("<percent>", String.valueOf(terms.getPercent().intValue()));
        if (terms.getDays() == 90 || terms.getDays() == 100)
            termsString = termsString.replaceAll("<days>", terms.getDays() + "-та рабочих дней");
        else if (terms.getDays() == 40)
            termsString = termsString.replaceAll("<days>", terms.getDays() + "-ка рабочих дней");
        else if (findLastDigits(terms.getDays()) == 1 && terms.getDays() != 11 && terms.getDays() != 111)
            termsString = termsString.replaceAll("<days>", terms.getDays() + "-го рабочего дня");
        else if (findLastDigits(terms.getDays()) == 2 || findLastDigits(terms.getDays()) == 3 || findLastDigits(terms.getDays()) == 4)
            termsString = termsString.replaceAll("<days>", terms.getDays() + "-х рабочих дней");
        else
            termsString = termsString.replaceAll("<days>", terms.getDays() + "-ти рабочих дней");
        return termsString;
    }

    private int findLastDigits(int number) {
        int lastDigit = number % 10;
        return lastDigit;
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
        copyRows(73, 84, cursor, workbook.getSheet(SHEET_NAME));
        cursor += 12;
        printCell(String.valueOf(count), startRow, 1);
        if (!Objects.isNull(item.getProduct())) {
            double costs = item.getProduct().getPrice() == null ? 0 : item.getProduct().getPrice() * item.getAmount();
            double totalWeight = item.getProduct().getWeight() == null ? 0 : item.getProduct().getWeight() * item.getAmount();
            this.totalCost += costs;
            this.totalWeight += totalWeight;
            ReducerEntity reducer = item.getProduct().getReducer();
            printCell(item.getProduct().getName(), startRow, 2);
            printCell(String.valueOf(item.getAmount()), startRow, 4);
            printCell(formatMoney(item.getProduct().getPrice()), startRow, 7);
            printCell(formatMoney(costs), startRow, 8);
            printCell(reducer.getReducerType().getReducerTypeName(), startRow + 2, 3);
            printCell(String.valueOf(reducer.getRatio()), startRow + 3, 3);
            printCell(reducer.getReducerMounting().getReducerMountingValue(), startRow + 4, 3);
            printCell(
                    reducer.getReducerInstallationType().getReducerInstallationTypeValue()
                            .toLowerCase().contains("флан") ? "Да" : "Нет", startRow + 5, 3);
            printCell(String.valueOf(reducer.getReducerOutputShaftType().getReducerOutputShaftTypeValue()), startRow + 7, 3);
            printCell("Ø" + reducer.getDiameterOutputShaft(), startRow + 8, 3);
            printCell(String.valueOf(item.getProduct().getWeight()), startRow + 9, 3);
            printOptions(item, startRow, 10);
        }
    }

    private void fillMotor(CommercialPropItemEntity item, int count) {
        int startRow = cursor;
        copyRows(60, 71, cursor, workbook.getSheet(SHEET_NAME));
        cursor += 12;
        printCell(String.valueOf(count), startRow, 1);
        if (!Objects.isNull(item.getProduct())) {
            double costs = item.getProduct().getPrice() == null ? 0 : item.getProduct().getPrice() * item.getAmount();
            double totalWeight = item.getProduct().getWeight() == null ? 0 : item.getProduct().getWeight() * item.getAmount();
            this.totalCost += costs;
            this.totalWeight += totalWeight;
            MotorEntity motor = item.getProduct().getMotor();
            printCell(item.getProduct().getName(), startRow, 2);
            printCell(String.valueOf(item.getAmount()), startRow, 4);
            printCell(formatMoney(item.getProduct().getPrice()), startRow, 7);
            printCell(formatMoney(costs), startRow, 8);
            printCell(String.valueOf(item.getProduct().getRpm()), startRow + 2, 3);
            printCell(String.valueOf(motor.getPower()), startRow + 3, 3);
            printCell(String.valueOf(motor.getEfficiency()), startRow + 4, 3);
            printCell(String.valueOf(motor.getRatedCurrent()), startRow + 5, 3);
            printCell(String.valueOf(motor.getMomentOfInertia()), startRow + 8, 3);
            printCell(String.valueOf(item.getProduct().getWeight()), startRow + 9, 3);
            printOptions(item, startRow, 10);
        }
    }

    private void fillMotorReducer(CommercialPropItemEntity item, int count) {
        int startRow = cursor;
        copyRows(41, 58, cursor, workbook.getSheet(SHEET_NAME));
        cursor += 18;
        printCell(String.valueOf(count), startRow, 1);
        if (!Objects.isNull(item.getProduct())) {
            double costs = item.getProduct().getPrice() == null ? 0 : item.getProduct().getPrice() * item.getAmount();
            double totalWeight = item.getProduct().getWeight() == null ? 0 : item.getProduct().getWeight() * item.getAmount();
            this.totalCost += costs;
            this.totalWeight += totalWeight;
            MotorEntity motor = item.getProduct().getMotor();
            ReducerEntity reducer = item.getProduct().getReducer();
            printCell(item.getProduct().getName(), startRow, 2);
            printCell(String.valueOf(item.getAmount()), startRow, 4);
            printCell(formatMoney(item.getProduct().getPrice()), startRow, 7);
            printCell(formatMoney(costs), startRow, 8);
            printCell(String.valueOf(reducer.getReducerType().getReducerTypeName()), startRow + 2, 3);
            printCell(String.valueOf(item.getProduct().getRpm()), startRow + 3, 3);
            printCell(String.valueOf(motor.getPower()), startRow + 4, 3);
            printCell(String.valueOf(reducer.getRatio()), startRow + 5, 3);
            printCell(String.valueOf(item.getProduct().getServiceFactor()), startRow + 6, 3);
            printCell(String.valueOf(reducer.getReducerMounting().getReducerMountingValue()), startRow + 9, 3);
            printCell((reducer.getReducerInstallationType().getReducerInstallationTypeValue()
                    .toLowerCase().contains("флан") ? "Да" : "Нет"), startRow + 10, 3);
            printCell(String.valueOf(reducer.getReducerOutputShaftType().getReducerOutputShaftTypeValue()), startRow + 12, 3);
            printCell("Ø" + reducer.getDiameterOutputShaft(), startRow + 13, 3);
            printCell(String.valueOf(item.getProduct().getTorqueMoment()), startRow + 14, 3);
            printCell(String.valueOf(item.getProduct().getWeight()), startRow + 15, 3);
            printOptions(item, startRow, 16);
        }
    }

    private void printOptions(CommercialPropItemEntity item, int startRow, int firstItemRow) {
        if (item.getProduct().getOptions() != null && !item.getProduct().getOptions().isEmpty()) {
            if (item.getProduct().getOptions().size() > 1) {
                cancelMerged(workbook.getSheet(SHEET_NAME), startRow, cursor - 1, 1, 1);
                cancelMerged(workbook.getSheet(SHEET_NAME), startRow, cursor - 1, 4, 6);
                cancelMerged(workbook.getSheet(SHEET_NAME), startRow, cursor - 1, 7, 7);
                cancelMerged(workbook.getSheet(SHEET_NAME), startRow, cursor - 1, 8, 9);
                List<ProductOptionEntity> options = item.getProduct().getOptions().stream().toList();
                for (int i = 0; i < options.size(); i++) {
                    if (i == 0)
                        printCell(options.get(i).getProductOptionValue(), startRow + firstItemRow, 3);
                    else {
                        copyRows(startRow + firstItemRow + i, startRow + firstItemRow + i, cursor, workbook.getSheet(SHEET_NAME));
                        printCell(options.get(i).getProductOptionValue(), startRow + firstItemRow + i, 3);
                        cursor++;
                    }
                }
                workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 1, 1));
                workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 4, 6));
                workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 7, 7));
                workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 8, 9));
            } else {
                ProductOptionEntity option = item.getProduct().getOptions().stream().findFirst().get();
                printCell(option.getProductOptionValue(), startRow + firstItemRow, 3);
            }
        } else {
            printCell("Отсутствуют", startRow + firstItemRow, 3);
        }
    }

    private void printCell(String value, int rowNumber, int cellNumber) {
        if (value == null || value.equals("null"))
            value = "";
        XSSFRow row = workbook.getSheet(SHEET_NAME).getRow(rowNumber);
        if (row == null) {
            for (int i = rowNumber; i < workbook.getSheet(SHEET_NAME).getLastRowNum(); i++) {
                row = workbook.getSheet(SHEET_NAME).getRow(i);
                cursor++;
                if (row != null)
                    break;
            }
            row.getCell(cellNumber).setCellValue(value);
        }
        row.getCell(cellNumber).setCellValue(value);
    }

    private String formatMoney(Double money) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("###,###.00", symbols);
        return df.format(money);
    }

    public static void copyRows(int startRow, int endRow, int pPosition, XSSFSheet sheet) {
        int pStartRow = startRow;
        int pEndRow = endRow;
        int targetRowFrom;
        int targetRowTo;
        int columnCount;
        CellRangeAddress region = null;
        int i;
        int j;
        if (pStartRow == -1 || pEndRow == -1) {
            return;
        }
        for (i = 0; i < sheet.getNumMergedRegions(); i++) {
            region = sheet.getMergedRegion(i);
            if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
                targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                targetRowTo = region.getLastRow() - pStartRow + pPosition;
                CellRangeAddress newRegion = region.copy();
                newRegion.setFirstRow(targetRowFrom);
                newRegion.setFirstColumn(region.getFirstColumn());
                newRegion.setLastRow(targetRowTo);
                newRegion.setLastColumn(region.getLastColumn());
                sheet.addMergedRegion(newRegion);
            }
        }
        for (i = pStartRow; i <= pEndRow; i++) {
            XSSFRow sourceRow = sheet.getRow(i);
            columnCount = sourceRow.getLastCellNum();
            if (sourceRow != null) {
                XSSFRow newRow = sheet.createRow(pPosition - pStartRow + i);
                newRow.setHeight(sourceRow.getHeight());
                for (j = 0; j < columnCount; j++) {
                    XSSFCell templateCell = sourceRow.getCell(j);
                    if (templateCell != null) {
                        XSSFCell newCell = newRow.createCell(j);
                        copyCell(templateCell, newCell);
                    }
                }
            }
        }
    }

    public static void copyCell(XSSFCell srcCell, XSSFCell distCell) {
        distCell.setCellStyle(srcCell.getCellStyle());
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        CellType srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (srcCellType.equals(CellType.NUMERIC)) {
            distCell.setCellValue(srcCell.getNumericCellValue());
        } else if (srcCellType.equals(CellType.STRING)) {
            distCell.setCellValue(srcCell.getRichStringCellValue());
        } else if (srcCellType.equals(CellType.BLANK)) {
            // nothing21
        } else if (srcCellType.equals(CellType.BOOLEAN)) {
            distCell.setCellValue(srcCell.getBooleanCellValue());
        } else if (srcCellType.equals(CellType.ERROR)) {
            distCell.setCellErrorValue(srcCell.getErrorCellValue());
        } else if (srcCellType.equals(CellType.FORMULA)) {
            distCell.setCellFormula(srcCell.getCellFormula());
        } else { // nothing29

        }
    }

    public XSSFRow insertRow(XSSFSheet sheet, int rowIndex) {
        XSSFRow row = null;
        if (sheet.getRow(rowIndex) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(rowIndex, lastRowNo, 1);
        }
        row = sheet.createRow(rowIndex);
        return row;
    }

    public void cancelMerged(XSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        List<CellRangeAddress> listRange = sheet.getMergedRegions();

        List<Integer> removeRangeNums = new ArrayList<Integer>();
        CellRangeAddress r = null;
        for (int i = 0; i < listRange.size(); i++) {
            r = listRange.get(i);
            if (r.getFirstRow() >= firstRow && r.getLastRow() <= lastRow && r.getFirstColumn() >= firstCol && r.getLastColumn() <= lastCol) {
                removeRangeNums.add(i);
            }
        }
        sheet.removeMergedRegions(removeRangeNums);
    }

    public void deleteRow(XSSFSheet sheet, int startRow, int endRow) {
        int maxColNum = 0;
        Iterator<Row> rowIt = sheet.rowIterator();
        while (rowIt.hasNext()) {
            maxColNum = Math.max(maxColNum, rowIt.next().getLastCellNum());
        }

        //Move Downward
        int startNumDown = endRow + 1;
        int endNumDown = sheet.getLastRowNum();
        int moveNumDown = endNumDown - startNumDown + 1;
        sheet.shiftRows(startNumDown, endNumDown, moveNumDown, true, true);


        // The blank part cancel the merger processing
        cancelMerged(sheet, startRow, endRow + moveNumDown, 0, maxColNum);
        // Reset this part
        for (int i = startRow; i < endRow + moveNumDown; i++) {
            sheet.createRow(i);
            sheet.removeRow(sheet.getRow(i));
        }

        // Move back up again
        int startNumUp = endNumDown + 1;
        int endNumUp = sheet.getLastRowNum();
        int moveNumUp = moveNumDown + (endRow - startRow) + 1;
        sheet.shiftRows(startNumUp, endNumUp, -1 * moveNumUp, true, true);
    }
}
