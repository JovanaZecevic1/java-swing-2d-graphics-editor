package mvc;
import geometry.Shape;
import java.awt.Color;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.AddShapeCommand;
import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.Command;
import command.DeselectCommand;
import command.RemoveShapeCommand;
import command.SelectCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import command.UpdateCircleCommand;
import command.UpdateDonutCommand;
import command.UpdateHexagonCommand;
import command.UpdateLineCommand;
import command.UpdatePointCommand;
import command.UpdateRectangleCommand;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

public class CommandParser {
private ControllerDrawing controller;
    
    // Stack-ovi za replay funkcionalnost
    private Stack<Command> replayExecutedCommands;
    private Stack<Command> replayUndoneCommands;
    
    public CommandParser(ControllerDrawing controller) {
        this.controller = controller;
        this.replayExecutedCommands = new Stack<>();
        this.replayUndoneCommands = new Stack<>();
    }
    
    // Resetuje stack-ove pre nego što počne replay
    public void resetStacks() {
        replayExecutedCommands.clear();
        replayUndoneCommands.clear();
    }
    
    public void parse(String commandString) {
        try {
            String operationType = determineOperationType(commandString);
            
            switch(operationType) {
                case "UNDO":
                    processUndo();
                    break;
                case "REDO":
                    processRedo();
                    break;
                case "ADD":
                    processAdd(commandString);
                    break;
                case "UPDATE":
                    processModification(commandString);
                    break;
                case "DELETE":
                    processDelete(commandString);
                    break;
                case "TOFRONT":
                    processToFront(commandString);
                    break;
                case "TOBACK":
                    processToBack(commandString);
                    break;
                case "BRINGTOFRONT":
                    processBringToFront(commandString);
                    break;
                case "BRINGTOBACK":
                    processBringToBack(commandString);
                    break;
                case "SELECT":              // ← NOVO
                    processSelect(commandString);
                    break;
                case "DESELECT":            // ← NOVO
                    processDeselect(commandString);
                    break;
                default:
                    System.err.println("Unknown operation: " + commandString);
            }
        } catch (Exception e) {
            System.err.println("Error parsing command: " + commandString);
            e.printStackTrace();
        }
    }
    
    private String determineOperationType(String cmd) {
        if (cmd.startsWith("Undo:")) return "UNDO";
        if (cmd.startsWith("Redo:")) return "REDO";
        if (cmd.startsWith("Added:")) return "ADD";
        if (cmd.startsWith("Updated:")) return "UPDATE";
        if (cmd.startsWith("Removed:")) return "DELETE";
        if (cmd.startsWith("Bring To Front:")) return "BRINGTOFRONT";
        if (cmd.startsWith("Bring To Back:")) return "BRINGTOBACK";
        if (cmd.startsWith("To Front:")) return "TOFRONT";
        if (cmd.startsWith("To Back:")) return "TOBACK";
        if (cmd.startsWith("Selected:")) return "SELECT";     
        if (cmd.startsWith("Deselected:")) return "DESELECT";  
        return "UNKNOWN";
    }
    
    private void processSelect(String cmd) {
        Shape shape = findShapeInCommand(cmd);
        if (shape != null) {
            SelectCommand command = new SelectCommand(controller.getModel(), shape);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }

    private void processDeselect(String cmd) {
        Shape shape = findShapeInCommand(cmd);
        if (shape != null) {
            DeselectCommand command = new DeselectCommand(controller.getModel(), shape);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }
    
    
    private void processUndo() {
        if (!replayExecutedCommands.isEmpty()) {
            Command lastCommand = replayExecutedCommands.pop();
            lastCommand.unexecute();
            replayUndoneCommands.push(lastCommand);
            controller.getFrame().repaint();
        }
    }
    
    private void processRedo() {
        if (!replayUndoneCommands.isEmpty()) {
            Command commandToRedo = replayUndoneCommands.pop();
            commandToRedo.execute();
            replayExecutedCommands.push(commandToRedo);
            controller.getFrame().repaint();
        }
    }
    
    private void processAdd(String cmd) {
        String shapeType = identifyShape(cmd);
        
        switch(shapeType) {
            case "POINT":
                createPoint(cmd);
                break;
            case "LINE":
                createLine(cmd);
                break;
            case "RECTANGLE":
                createRectangle(cmd);
                break;
            case "CIRCLE":
                createCircle(cmd);
                break;
            case "DONUT":
                createDonut(cmd);
                break;
            case "HEXAGON":
                createHexagon(cmd);
                break;
        }
    }
    
    private void processModification(String cmd) {
        String shapeType = identifyShape(cmd);
        
        switch(shapeType) {
            case "POINT":
                modifyPoint(cmd);
                break;
            case "LINE":
                modifyLine(cmd);
                break;
            case "RECTANGLE":
                modifyRectangle(cmd);
                break;
            case "CIRCLE":
                modifyCircle(cmd);
                break;
            case "DONUT":
                modifyDonut(cmd);
                break;
            case "HEXAGON":
                modifyHexagon(cmd);
                break;
        }
    }
    
    private void processDelete(String cmd) {
        String shapeType = identifyShape(cmd);
        
        switch(shapeType) {
            case "POINT":
                deletePoint(cmd);
                break;
            case "LINE":
                deleteLine(cmd);
                break;
            case "RECTANGLE":
                deleteRectangle(cmd);
                break;
            case "CIRCLE":
                deleteCircle(cmd);
                break;
            case "DONUT":
                deleteDonut(cmd);
                break;
            case "HEXAGON":
                deleteHexagon(cmd);
                break;
        }
    }
    
    private String identifyShape(String cmd) {
        if (cmd.contains("HexagonAdapter(")) return "HEXAGON";
        if (cmd.contains("Donut(")) return "DONUT";
        if (cmd.contains("Circle(")) return "CIRCLE";
        if (cmd.contains("Rectangle(")) return "RECTANGLE";
        if (cmd.contains("Line(")) return "LINE";
        if (cmd.contains("Point(")) return "POINT";
        return "UNKNOWN";
    }
    
    private void createPoint(String cmd) {
        Pattern pattern = Pattern.compile("Point\\((\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int colorRGB = Integer.parseInt(matcher.group(3));
            
            Point point = new Point(x, y);
            point.setOutlineColor(new Color(colorRGB));
            
            AddShapeCommand command = new AddShapeCommand(controller.getModel(), point);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }
    
    private void createLine(String cmd) {
        Pattern pattern = Pattern.compile("Line\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x1 = Integer.parseInt(matcher.group(1));
            int y1 = Integer.parseInt(matcher.group(2));
            int x2 = Integer.parseInt(matcher.group(3));
            int y2 = Integer.parseInt(matcher.group(4));
            int colorRGB = Integer.parseInt(matcher.group(5));
            
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line line = new Line(start, end);
            line.setOutlineColor(new Color(colorRGB));
            
            AddShapeCommand command = new AddShapeCommand(controller.getModel(), line);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }
    
    private void createRectangle(String cmd) {
        Pattern pattern = Pattern.compile("Rectangle\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int width = Integer.parseInt(matcher.group(3));
            int height = Integer.parseInt(matcher.group(4));
            int outlineRGB = Integer.parseInt(matcher.group(5));
            int innerRGB = Integer.parseInt(matcher.group(6));
            
            Point upperLeft = new Point(x, y);
            Rectangle rectangle = new Rectangle(upperLeft, height, width);
            rectangle.setOutlineColor(new Color(outlineRGB));
            rectangle.setInnerColor(new Color(innerRGB));
            
            AddShapeCommand command = new AddShapeCommand(controller.getModel(), rectangle);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }
    
    private void createCircle(String cmd) {
        Pattern pattern = Pattern.compile("Circle\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int outlineRGB = Integer.parseInt(matcher.group(4));
            int innerRGB = Integer.parseInt(matcher.group(5));
            
            Point center = new Point(x, y);
            Circle circle = new Circle(center, radius);
            circle.setOutlineColor(new Color(outlineRGB));
            circle.setInnerColor(new Color(innerRGB));
            
            AddShapeCommand command = new AddShapeCommand(controller.getModel(), circle);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }
    
    private void createDonut(String cmd) {
        Pattern pattern = Pattern.compile("Donut\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int innerRadius = Integer.parseInt(matcher.group(4));
            int outlineRGB = Integer.parseInt(matcher.group(5));
            int innerRGB = Integer.parseInt(matcher.group(6));
            
            Point center = new Point(x, y);
            Donut donut = new Donut(center, radius, innerRadius);
            donut.setOutlineColor(new Color(outlineRGB));
            donut.setInnerColor(new Color(innerRGB));
            
            AddShapeCommand command = new AddShapeCommand(controller.getModel(), donut);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }
    
    private void createHexagon(String cmd) {
        Pattern pattern = Pattern.compile("HexagonAdapter\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int outlineRGB = Integer.parseInt(matcher.group(4));
            int innerRGB = Integer.parseInt(matcher.group(5));
            
            HexagonAdapter hexagon = new HexagonAdapter(x, y, radius);
            hexagon.setOutlineColor(new Color(outlineRGB));
            hexagon.setInnerColor(new Color(innerRGB));
            
            AddShapeCommand command = new AddShapeCommand(controller.getModel(), hexagon);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }
    
    
    private void modifyPoint(String cmd) {
        Pattern pattern = Pattern.compile("Point\\((\\d+),(\\d+),(-?\\d+)\\)->Point\\((\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int oldX = Integer.parseInt(matcher.group(1));
            int oldY = Integer.parseInt(matcher.group(2));
            int oldColorRGB = Integer.parseInt(matcher.group(3));
            
            int newX = Integer.parseInt(matcher.group(4));
            int newY = Integer.parseInt(matcher.group(5));
            int newColorRGB = Integer.parseInt(matcher.group(6));
            
            Point originalPoint = findPointInModel(oldX, oldY, oldColorRGB);
            if (originalPoint != null) {
                Point updatedPoint = new Point(newX, newY);
                updatedPoint.setOutlineColor(new Color(newColorRGB));
                
                UpdatePointCommand command = new UpdatePointCommand(originalPoint, updatedPoint);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void modifyLine(String cmd) {
        Pattern pattern = Pattern.compile("Line\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+)\\)->Line\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int oldX1 = Integer.parseInt(matcher.group(1));
            int oldY1 = Integer.parseInt(matcher.group(2));
            int oldX2 = Integer.parseInt(matcher.group(3));
            int oldY2 = Integer.parseInt(matcher.group(4));
            int oldColorRGB = Integer.parseInt(matcher.group(5));
            
            int newX1 = Integer.parseInt(matcher.group(6));
            int newY1 = Integer.parseInt(matcher.group(7));
            int newX2 = Integer.parseInt(matcher.group(8));
            int newY2 = Integer.parseInt(matcher.group(9));
            int newColorRGB = Integer.parseInt(matcher.group(10));
            
            Line originalLine = findLineInModel(oldX1, oldY1, oldX2, oldY2, oldColorRGB);
            if (originalLine != null) {
                Point newStart = new Point(newX1, newY1);
                Point newEnd = new Point(newX2, newY2);
                Line updatedLine = new Line(newStart, newEnd);
                updatedLine.setOutlineColor(new Color(newColorRGB));
                
                UpdateLineCommand command = new UpdateLineCommand(originalLine, updatedLine);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void modifyRectangle(String cmd) {
        Pattern pattern = Pattern.compile("Rectangle\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)->Rectangle\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int oldX = Integer.parseInt(matcher.group(1));
            int oldY = Integer.parseInt(matcher.group(2));
            int oldWidth = Integer.parseInt(matcher.group(3));
            int oldHeight = Integer.parseInt(matcher.group(4));
            int oldOutlineRGB = Integer.parseInt(matcher.group(5));
            int oldInnerRGB = Integer.parseInt(matcher.group(6));
            
            int newX = Integer.parseInt(matcher.group(7));
            int newY = Integer.parseInt(matcher.group(8));
            int newWidth = Integer.parseInt(matcher.group(9));
            int newHeight = Integer.parseInt(matcher.group(10));
            int newOutlineRGB = Integer.parseInt(matcher.group(11));
            int newInnerRGB = Integer.parseInt(matcher.group(12));
            
            Rectangle originalRect = findRectangleInModel(oldX, oldY, oldWidth, oldHeight, oldOutlineRGB, oldInnerRGB);
            if (originalRect != null) {
                Point newUpperLeft = new Point(newX, newY);
                Rectangle updatedRect = new Rectangle(newUpperLeft, newHeight, newWidth);
                updatedRect.setOutlineColor(new Color(newOutlineRGB));
                updatedRect.setInnerColor(new Color(newInnerRGB));
                
                UpdateRectangleCommand command = new UpdateRectangleCommand(originalRect, updatedRect);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void modifyCircle(String cmd) {
        Pattern pattern = Pattern.compile("Circle\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)->Circle\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int oldX = Integer.parseInt(matcher.group(1));
            int oldY = Integer.parseInt(matcher.group(2));
            int oldRadius = Integer.parseInt(matcher.group(3));
            int oldOutlineRGB = Integer.parseInt(matcher.group(4));
            int oldInnerRGB = Integer.parseInt(matcher.group(5));
            
            int newX = Integer.parseInt(matcher.group(6));
            int newY = Integer.parseInt(matcher.group(7));
            int newRadius = Integer.parseInt(matcher.group(8));
            int newOutlineRGB = Integer.parseInt(matcher.group(9));
            int newInnerRGB = Integer.parseInt(matcher.group(10));
            
            Circle originalCircle = findCircleInModel(oldX, oldY, oldRadius, oldOutlineRGB, oldInnerRGB);
            if (originalCircle != null) {
                Point newCenter = new Point(newX, newY);
                Circle updatedCircle = new Circle(newCenter, newRadius);
                updatedCircle.setOutlineColor(new Color(newOutlineRGB));
                updatedCircle.setInnerColor(new Color(newInnerRGB));
                
                UpdateCircleCommand command = new UpdateCircleCommand(originalCircle, updatedCircle);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void modifyDonut(String cmd) {
        Pattern pattern = Pattern.compile("Donut\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)->Donut\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int oldX = Integer.parseInt(matcher.group(1));
            int oldY = Integer.parseInt(matcher.group(2));
            int oldRadius = Integer.parseInt(matcher.group(3));
            int oldInnerRadius = Integer.parseInt(matcher.group(4));
            int oldOutlineRGB = Integer.parseInt(matcher.group(5));
            int oldInnerRGB = Integer.parseInt(matcher.group(6));
            
            int newX = Integer.parseInt(matcher.group(7));
            int newY = Integer.parseInt(matcher.group(8));
            int newRadius = Integer.parseInt(matcher.group(9));
            int newInnerRadius = Integer.parseInt(matcher.group(10));
            int newOutlineRGB = Integer.parseInt(matcher.group(11));
            int newInnerRGB = Integer.parseInt(matcher.group(12));
            
            Donut originalDonut = findDonutInModel(oldX, oldY, oldRadius, oldInnerRadius, oldOutlineRGB, oldInnerRGB);
            if (originalDonut != null) {
                Point newCenter = new Point(newX, newY);
                Donut updatedDonut = new Donut(newCenter, newRadius, newInnerRadius);
                updatedDonut.setOutlineColor(new Color(newOutlineRGB));
                updatedDonut.setInnerColor(new Color(newInnerRGB));
                
                UpdateDonutCommand command = new UpdateDonutCommand(originalDonut, updatedDonut);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void modifyHexagon(String cmd) {
        Pattern pattern = Pattern.compile("HexagonAdapter\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)->HexagonAdapter\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int oldX = Integer.parseInt(matcher.group(1));
            int oldY = Integer.parseInt(matcher.group(2));
            int oldRadius = Integer.parseInt(matcher.group(3));
            int oldOutlineRGB = Integer.parseInt(matcher.group(4));
            int oldInnerRGB = Integer.parseInt(matcher.group(5));
            
            int newX = Integer.parseInt(matcher.group(6));
            int newY = Integer.parseInt(matcher.group(7));
            int newRadius = Integer.parseInt(matcher.group(8));
            int newOutlineRGB = Integer.parseInt(matcher.group(9));
            int newInnerRGB = Integer.parseInt(matcher.group(10));
            
            HexagonAdapter originalHex = findHexagonInModel(oldX, oldY, oldRadius, oldOutlineRGB, oldInnerRGB);
            if (originalHex != null) {
                HexagonAdapter updatedHex = new HexagonAdapter(newX, newY, newRadius);
                updatedHex.setOutlineColor(new Color(newOutlineRGB));
                updatedHex.setInnerColor(new Color(newInnerRGB));
                
                UpdateHexagonCommand command = new UpdateHexagonCommand(originalHex, updatedHex);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }

    
    
    private void deletePoint(String cmd) {
        Pattern pattern = Pattern.compile("Point\\((\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int colorRGB = Integer.parseInt(matcher.group(3));
            
            Point pointToRemove = findPointInModel(x, y, colorRGB);
            if (pointToRemove != null) {
                RemoveShapeCommand command = new RemoveShapeCommand(controller.getModel(), pointToRemove);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void deleteLine(String cmd) {
        Pattern pattern = Pattern.compile("Line\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x1 = Integer.parseInt(matcher.group(1));
            int y1 = Integer.parseInt(matcher.group(2));
            int x2 = Integer.parseInt(matcher.group(3));
            int y2 = Integer.parseInt(matcher.group(4));
            int colorRGB = Integer.parseInt(matcher.group(5));
            
            Line lineToRemove = findLineInModel(x1, y1, x2, y2, colorRGB);
            if (lineToRemove != null) {
                RemoveShapeCommand command = new RemoveShapeCommand(controller.getModel(), lineToRemove);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void deleteRectangle(String cmd) {
        Pattern pattern = Pattern.compile("Rectangle\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int width = Integer.parseInt(matcher.group(3));
            int height = Integer.parseInt(matcher.group(4));
            int outlineRGB = Integer.parseInt(matcher.group(5));
            int innerRGB = Integer.parseInt(matcher.group(6));
            
            Rectangle rectToRemove = findRectangleInModel(x, y, width, height, outlineRGB, innerRGB);
            if (rectToRemove != null) {
                RemoveShapeCommand command = new RemoveShapeCommand(controller.getModel(), rectToRemove);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void deleteCircle(String cmd) {
        Pattern pattern = Pattern.compile("Circle\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int outlineRGB = Integer.parseInt(matcher.group(4));
            int innerRGB = Integer.parseInt(matcher.group(5));
            
            Circle circleToRemove = findCircleInModel(x, y, radius, outlineRGB, innerRGB);
            if (circleToRemove != null) {
                RemoveShapeCommand command = new RemoveShapeCommand(controller.getModel(), circleToRemove);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void deleteDonut(String cmd) {
        Pattern pattern = Pattern.compile("Donut\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int innerRadius = Integer.parseInt(matcher.group(4));
            int outlineRGB = Integer.parseInt(matcher.group(5));
            int innerRGB = Integer.parseInt(matcher.group(6));
            
            Donut donutToRemove = findDonutInModel(x, y, radius, innerRadius, outlineRGB, innerRGB);
            if (donutToRemove != null) {
                RemoveShapeCommand command = new RemoveShapeCommand(controller.getModel(), donutToRemove);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    private void deleteHexagon(String cmd) {
        Pattern pattern = Pattern.compile("HexagonAdapter\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int outlineRGB = Integer.parseInt(matcher.group(4));
            int innerRGB = Integer.parseInt(matcher.group(5));
            
            HexagonAdapter hexToRemove = findHexagonInModel(x, y, radius, outlineRGB, innerRGB);
            if (hexToRemove != null) {
                RemoveShapeCommand command = new RemoveShapeCommand(controller.getModel(), hexToRemove);
                command.execute();
                
                replayExecutedCommands.push(command);
                replayUndoneCommands.clear();
            }
        }
    }
    
    
    private void processToFront(String cmd) {
        Shape shape = findShapeInCommand(cmd);
        if (shape != null) {
            ToFrontCommand command = new ToFrontCommand(controller.getModel(), shape);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }

    private void processToBack(String cmd) {
        Shape shape = findShapeInCommand(cmd);
        if (shape != null) {
            ToBackCommand command = new ToBackCommand(controller.getModel(), shape);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }

    private void processBringToFront(String cmd) {
        Shape shape = findShapeInCommand(cmd);
        if (shape != null) {
            BringToFrontCommand command = new BringToFrontCommand(controller.getModel(), shape);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }

    private void processBringToBack(String cmd) {
        Shape shape = findShapeInCommand(cmd);
        if (shape != null) {
            BringToBackCommand command = new BringToBackCommand(controller.getModel(), shape);
            command.execute();
            
            replayExecutedCommands.push(command);
            replayUndoneCommands.clear();
        }
    }

    private Shape findShapeInCommand(String cmd) {
        String shapeType = identifyShape(cmd);
        
        switch(shapeType) {
            case "POINT":
                return findPointFromString(cmd);
            case "LINE":
                return findLineFromString(cmd);
            case "RECTANGLE":
                return findRectangleFromString(cmd);
            case "CIRCLE":
                return findCircleFromString(cmd);
            case "DONUT":
                return findDonutFromString(cmd);
            case "HEXAGON":
                return findHexagonFromString(cmd);
            default:
                return null;
        }
    }

    private Point findPointFromString(String cmd) {
        Pattern pattern = Pattern.compile("Point\\((\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int colorRGB = Integer.parseInt(matcher.group(3));
            return findPointInModel(x, y, colorRGB);
        }
        return null;
    }

    private Line findLineFromString(String cmd) {
        Pattern pattern = Pattern.compile("Line\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x1 = Integer.parseInt(matcher.group(1));
            int y1 = Integer.parseInt(matcher.group(2));
            int x2 = Integer.parseInt(matcher.group(3));
            int y2 = Integer.parseInt(matcher.group(4));
            int colorRGB = Integer.parseInt(matcher.group(5));
            return findLineInModel(x1, y1, x2, y2, colorRGB);
        }
        return null;
    }

    private Rectangle findRectangleFromString(String cmd) {
        Pattern pattern = Pattern.compile("Rectangle\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int width = Integer.parseInt(matcher.group(3));
            int height = Integer.parseInt(matcher.group(4));
            int outlineRGB = Integer.parseInt(matcher.group(5));
            int innerRGB = Integer.parseInt(matcher.group(6));
            return findRectangleInModel(x, y, width, height, outlineRGB, innerRGB);
        }
        return null;
    }

    private Circle findCircleFromString(String cmd) {
        Pattern pattern = Pattern.compile("Circle\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int outlineRGB = Integer.parseInt(matcher.group(4));
            int innerRGB = Integer.parseInt(matcher.group(5));
            return findCircleInModel(x, y, radius, outlineRGB, innerRGB);
        }
        return null;
    }

    private Donut findDonutFromString(String cmd) {
        Pattern pattern = Pattern.compile("Donut\\((\\d+),(\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int innerRadius = Integer.parseInt(matcher.group(4));
            int outlineRGB = Integer.parseInt(matcher.group(5));
            int innerRGB = Integer.parseInt(matcher.group(6));
            return findDonutInModel(x, y, radius, innerRadius, outlineRGB, innerRGB);
        }
        return null;
    }

    private HexagonAdapter findHexagonFromString(String cmd) {
        Pattern pattern = Pattern.compile("HexagonAdapter\\((\\d+),(\\d+),(\\d+),(-?\\d+),(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(cmd);
        
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int radius = Integer.parseInt(matcher.group(3));
            int outlineRGB = Integer.parseInt(matcher.group(4));
            int innerRGB = Integer.parseInt(matcher.group(5));
            return findHexagonInModel(x, y, radius, outlineRGB, innerRGB);
        }
        return null;
    }
    
   
    private Point findPointInModel(int x, int y, int colorRGB) {
        ModelDrawing model = controller.getModel();
        for (Shape shape : model.getAll()) {
            if (shape instanceof Point) {
                Point p = (Point) shape;
                if (p.getX() == x && p.getY() == y && 
                    p.getOutlineColor().getRGB() == colorRGB) {
                    return p;
                }
            }
        }
        return null;
    }
    
    private Line findLineInModel(int x1, int y1, int x2, int y2, int colorRGB) {
        ModelDrawing model = controller.getModel();
        for (Shape shape : model.getAll()) {
            if (shape instanceof Line) {
                Line l = (Line) shape;
                if (l.getStartPoint().getX() == x1 && l.getStartPoint().getY() == y1 &&
                    l.getEndPoint().getX() == x2 && l.getEndPoint().getY() == y2 &&
                    l.getOutlineColor().getRGB() == colorRGB) {
                    return l;
                }
            }
        }
        return null;
    }
    
    private Rectangle findRectangleInModel(int x, int y, int width, int height, int outlineRGB, int innerRGB) {
        ModelDrawing model = controller.getModel();
        for (Shape shape : model.getAll()) {
            if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                if (r.getUpperLeftPoint().getX() == x && r.getUpperLeftPoint().getY() == y &&
                    r.getWidth() == width && r.getHeight() == height &&
                    r.getOutlineColor().getRGB() == outlineRGB &&
                    r.getInnerColor().getRGB() == innerRGB) {
                    return r;
                }
            }
        }
        return null;
    }
    
    private Circle findCircleInModel(int x, int y, int radius, int outlineRGB, int innerRGB) {
        ModelDrawing model = controller.getModel();
        for (Shape shape : model.getAll()) {
            if (shape instanceof Circle && !(shape instanceof Donut)) {
                Circle c = (Circle) shape;
                if (c.getCenter().getX() == x && c.getCenter().getY() == y &&
                    c.getRadius() == radius &&
                    c.getOutlineColor().getRGB() == outlineRGB &&
                    c.getInnerColor().getRGB() == innerRGB) {
                    return c;
                }
            }
        }
        return null;
    }
    
    private Donut findDonutInModel(int x, int y, int radius, int innerRadius, int outlineRGB, int innerRGB) {
        ModelDrawing model = controller.getModel();
        for (Shape shape : model.getAll()) {
            if (shape instanceof Donut) {
                Donut d = (Donut) shape;
                if (d.getCenter().getX() == x && d.getCenter().getY() == y &&
                    d.getRadius() == radius && d.getInnerRadius() == innerRadius &&
                    d.getOutlineColor().getRGB() == outlineRGB &&
                    d.getInnerColor().getRGB() == innerRGB) {
                    return d;
                }
            }
        }
        return null;
    }
    
    private HexagonAdapter findHexagonInModel(int x, int y, int radius, int outlineRGB, int innerRGB) {
        ModelDrawing model = controller.getModel();
        for (Shape shape : model.getAll()) {
            if (shape instanceof HexagonAdapter) {
                HexagonAdapter h = (HexagonAdapter) shape;
                if (h.getX() == x && h.getY() == y &&
                    h.getRadius() == radius &&
                    h.getOutlineColor().getRGB() == outlineRGB &&
                    h.getInnerColor().getRGB() == innerRGB) {
                    return h;
                }
            }
        }
        return null;
    }


}
