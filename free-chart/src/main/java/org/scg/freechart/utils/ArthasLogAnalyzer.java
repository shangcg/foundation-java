package org.scg.freechart.utils;

/**
 * <p>
 *
 * </p>
 * @author shangchenguang
 * @date 2025/5/22
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArthasLogAnalyzer {

    public static void main(String[] args) {
        String logData = "`---ts=2025-04-18 10:35:53.926;thread_name=http-nio-8090-exec-10;id=84;is_daemon=true;priority=5;TCCL=org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedWebappClassLoader@3c39c739\n" +
                "    `---[268.544129ms] com.didiglobal.kefu.fitan.web.biz.sopengine.controller.FlowExecuteController:executeFlow()\n" +
                "        +---[99.93% 268.350129ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.service.FlowExecuteService:execute() #38\n" +
                "        |   +---[20.34% 54.588063ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.service.impl.FlowExecuteServiceImpl:prepareFlowExecuteContext()\n" +
                "        |   |   +---[0.07% 0.039188ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:<init>() #99\n" +
                "        |   |   +---[0.02% 0.009688ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getProcessInstanceId() #100\n" +
                "        |   |   +---[0.01% 0.007306ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setProcessInstanceId() #100\n" +
                "        |   |   +---[0.01% 0.004832ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getTaskId() #101\n" +
                "        |   |   +---[0.01% 0.005892ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setNodeTaskId() #101\n" +
                "        |   |   +---[0.01% 0.004285ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getFlowVariables() #102\n" +
                "        |   |   +---[0.01% 0.004818ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setFlowVariables() #102\n" +
                "        |   |   +---[0.01% 0.004139ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getCommitInfoList() #103\n" +
                "        |   |   +---[0.01% 0.004278ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setCommitInfoList() #103\n" +
                "        |   |   +---[0.01% 0.004268ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getDialogueInfoList() #104\n" +
                "        |   |   +---[0.01% 0.004428ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setDialogueInfoList() #104\n" +
                "        |   |   +---[0.01% 0.004023ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getChatHistory() #105\n" +
                "        |   |   +---[0.01% 0.004395ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setChatHistory() #105\n" +
                "        |   |   +---[0.01% 0.007488ms ] com.didiglobal.kefu.fitan.web.pojo.bo.sopengine.SopEngineContext:getCaller() #106\n" +
                "        |   |   +---[0.01% 0.003853ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setCaller() #106\n" +
                "        |   |   +---[0.01% 0.003937ms ] com.didiglobal.kefu.fitan.web.pojo.bo.sopengine.SopEngineContext:getTrafficType() #107\n" +
                "        |   |   +---[0.01% 0.005063ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setTrafficType() #107\n" +
                "        |   |   +---[0.01% 0.004566ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getUserAction() #108\n" +
                "        |   |   +---[0.01% 0.00801ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setUserAction() #108\n" +
                "        |   |   +---[0.01% 0.003553ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getFlowKey() #111\n" +
                "        |   |   +---[0.01% 0.003592ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getVersion() #111\n" +
                "        |   |   +---[99.12% 54.105249ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.tracker.FlowStateTracker:queryFlowConfigBo() #111\n" +
                "        |   |   +---[0.03% 0.015495ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:setFlowConfigBo() #112\n" +
                "        |   |   +---[0.01% 0.005379ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getProcessInstanceId() #115\n" +
                "        |   |   +---[0.04% 0.024031ms ] org.apache.commons.lang3.StringUtils:isNotBlank() #115\n" +
                "        |   |   +---[0.01% 0.004173ms ] com.didiglobal.kefu.fitan.web.pojo.param.sopengine.FlowExecuteParam:getTaskId() #120\n" +
                "        |   |   `---[0.01% 0.004129ms ] org.apache.commons.lang3.StringUtils:isNotBlank() #120\n" +
                "        |   `---[78.72% 211.257002ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.SopFlowEngine:processInternal()\n" +
                "        |       +---[0.02% 0.035701ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.SopFlowEngine:initRunningCondition() #70\n" +
                "        |       +---[0.00% 0.008472ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:getCaller() #72\n" +
                "        |       +---[0.00% min=0.002127ms,max=0.004372ms,total=0.009964ms,count=3] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.SopFlowRunningCondition:isRunningSignal() #73\n" +
                "        |       +---[0.33% min=0.196222ms,max=0.504226ms,total=0.700448ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.SopFlowEngine:logRunningState() #75\n" +
                "        |       +---[0.35% min=0.143542ms,max=0.590652ms,total=0.734194ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.extractors.SlotExtractor:extract() #77\n" +
                "        |       +---[0.24% min=0.199582ms,max=0.297154ms,total=0.496736ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.ood.FlowOODDetector:detectOOD() #79\n" +
                "        |       +---[0.15% min=0.025041ms,max=0.286217ms,total=0.311258ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.DialogueContextHelper:updateDialogueContext() #81\n" +
                "        |       +---[0.17% min=0.178452ms,max=0.189666ms,total=0.368118ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.policies.ProcessPolicy:predictAction() #83\n" +
                "        |       +---[98.06% min=16.914618ms,max=190.25303ms,total=207.167648ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.ActionRunner:run() #85\n" +
                "        |       |   `---[99.94% min=16.891341ms,max=190.142509ms,total=207.03385ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.DelegateActionRunner:run()\n" +
                "        |       |       +---[0.01% min=0.002777ms,max=0.017028ms,total=0.019805ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.actions.ProcessAction:getActionType() #50\n" +
                "        |       |       +---[0.13% min=0.117141ms,max=0.146881ms,total=0.264022ms,count=2] com.didiglobal.reportlogger.ReportLogger:info() #50\n" +
                "        |       |       +---[0.00% min=0.002159ms,max=0.00358ms,total=0.005739ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.actions.ProcessAction:getActionType() #51\n" +
                "        |       |       +---[0.00% min=0.001903ms,max=0.004955ms,total=0.006858ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.ActionTypeEnums:getType() #51\n" +
                "        |       |       +---[0.01% min=0.002536ms,max=0.012189ms,total=0.014725ms,count=2] org.apache.commons.lang3.StringUtils:equals() #51\n" +
                "        |       |       +---[0.06% 0.126106ms ] com.didiglobal.reportlogger.ReportLogger:info() #52\n" +
                "        |       |       +---[91.65% 189.752821ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.flow.CreateInstanceRunner:run() #53\n" +
                "        |       |       |   `---[99.96% 189.675281ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:run()\n" +
                "        |       |       |       +---[0.01% 0.011381ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:preActionRun() #26\n" +
                "        |       |       |       +---[67.04% 127.156552ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:runInternal() #27\n" +
                "        |       |       |       |   `---[99.98% 127.126178ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.flow.CreateInstanceRunner:runInternal()\n" +
                "        |       |       |       |       `---[99.98% 127.106772ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.flow.CreateInstanceRunner:runInternal() #36\n" +
                "        |       |       |       |           `---[99.98% 127.084373ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.flow.CreateInstanceRunner:runInternal()\n" +
                "        |       |       |       |               +---[0.06% 0.079221ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.flow.CreateInstanceRunner:buildOptimusCreateRequest() #66\n" +
                "        |       |       |       |               +---[99.85% 126.895741ms ] com.didiglobal.kefu.fitan.web.rpc.OptimusPrimeInvoker:createInstance() #67\n" +
                "        |       |       |       |               `---[0.03% 0.031833ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.flow.CreateInstanceRunner:buildCreateInstanceResult() #68\n" +
                "        |       |       |       `---[32.87% 62.345788ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:postActionRun() #36\n" +
                "        |       |       +---[0.00% 0.002635ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.actions.ProcessAction:getActionType() #55\n" +
                "        |       |       +---[0.00% 0.002949ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.ActionTypeEnums:getType() #55\n" +
                "        |       |       +---[0.00% 0.0037ms ] org.apache.commons.lang3.StringUtils:equals() #55\n" +
                "        |       |       +---[0.00% 0.002776ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.actions.ProcessAction:getActionType() #59\n" +
                "        |       |       +---[0.00% 0.002708ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.ActionTypeEnums:getType() #59\n" +
                "        |       |       +---[0.00% 0.002972ms ] org.apache.commons.lang3.StringUtils:equals() #59\n" +
                "        |       |       +---[0.00% 0.002719ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.actions.ProcessAction:getActionType() #63\n" +
                "        |       |       +---[0.00% 0.002684ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.ActionTypeEnums:getType() #63\n" +
                "        |       |       +---[0.00% 0.0028ms ] org.apache.commons.lang3.StringUtils:equals() #63\n" +
                "        |       |       +---[0.12% 0.24896ms ] com.didiglobal.reportlogger.ReportLogger:info() #64\n" +
                "        |       |       `---[7.93% 16.407726ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.DelegateDialogueActionRunner:run() #65\n" +
                "        |       |           `---[99.30% 16.293531ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.DelegateDialogueActionRunner:run()\n" +
                "        |       |               +---[0.09% 0.014703ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:getCurrentActiveNode() #47\n" +
                "        |       |               +---[0.02% 0.003499ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:getCurrentActiveNode() #51\n" +
                "        |       |               +---[0.06% 0.01037ms ] com.didiglobal.kefu.fitan.web.pojo.bo.sopengine.FlowNodeStateBo:getNodeType() #51\n" +
                "        |       |               +---[0.03% 0.005335ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.NodeTypeEnums:getType() #52\n" +
                "        |       |               +---[0.07% 0.010962ms ] org.apache.commons.lang3.StringUtils:equals() #52\n" +
                "        |       |               +---[0.02% 0.002688ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.NodeTypeEnums:getType() #56\n" +
                "        |       |               +---[0.02% 0.003062ms ] org.apache.commons.lang3.StringUtils:equals() #56\n" +
                "        |       |               +---[0.01% 0.002265ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.NodeTypeEnums:getType() #60\n" +
                "        |       |               +---[0.02% 0.002897ms ] org.apache.commons.lang3.StringUtils:equals() #60\n" +
                "        |       |               +---[0.02% 0.003786ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.enums.NodeTypeEnums:getType() #64\n" +
                "        |       |               +---[0.02% 0.003907ms ] org.apache.commons.lang3.StringUtils:equals() #64\n" +
                "        |       |               +---[1.25% 0.20444ms ] com.didiglobal.reportlogger.ReportLogger:info() #65\n" +
                "        |       |               `---[97.21% 15.839559ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.DialogueActionRunner:run() #66\n" +
                "        |       |                   `---[99.72% 15.795307ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:run()\n" +
                "        |       |                       +---[0.43% 0.067559ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:preActionRun() #26\n" +
                "        |       |                       +---[3.84% 0.606229ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:runInternal() #27\n" +
                "        |       |                       |   `---[91.92% 0.557261ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.VoiceCollectNodeDialogueActionRunner:runInternal()\n" +
                "        |       |                       |       `---[96.17% 0.535919ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.VoiceCollectNodeDialogueActionRunner:runInternal() #55\n" +
                "        |       |                       |           `---[95.91% 0.51399ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.VoiceCollectNodeDialogueActionRunner:runInternal()\n" +
                "        |       |                       |               +---[1.53% 0.007874ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:getFlowNodeConfigBo() #109\n" +
                "        |       |                       |               +---[2.10% 0.010805ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.NodeSchemaBuilder:getVoiceDataColNodeSchema() #109\n" +
                "        |       |                       |               +---[0.64% 0.00329ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.FlowExecuteContext:getCurrentActiveNode() #110\n" +
                "        |       |                       |               +---[2.09% 0.010751ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runtime.DialogueContextHelper:getVoiceCollectNodeInstanceData() #110\n" +
                "        |       |                       |               +---[1.26% 0.00649ms ] com.didiglobal.kefu.fitan.web.pojo.bo.sopengine.nodeinstance.VoiceCollectNodeInstanceData:getDialogueContext() #118\n" +
                "        |       |                       |               +---[11.26% 0.057889ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.VoiceCollectNodeDialogueActionRunner:getNodeRoundNum() #118\n" +
                "        |       |                       |               +---[2.44% 0.012549ms ] com.didiglobal.kefu.fitan.web.pojo.bo.sopengine.nodeschema.VoiceColNodeSchema:getScriptList() #119\n" +
                "        |       |                       |               +---[1.50% 0.007735ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.VoiceCollectNodeDialogueActionRunner:getSchemaAnswerNum() #119\n" +
                "        |       |                       |               +---[36.82% 0.189228ms ] com.didiglobal.reportlogger.ReportLogger:info() #120\n" +
                "        |       |                       |               +---[0.78% 0.004026ms ] com.didiglobal.kefu.fitan.web.pojo.bo.sopengine.nodeschema.VoiceColNodeSchema:getScriptList() #121\n" +
                "        |       |                       |               +---[1.17% 0.006024ms ] com.didiglobal.kefu.fitan.web.pojo.bo.sopengine.nodeschema.VoiceColNodeSchema$ScriptContent:getAnswer() #121\n" +
                "        |       |                       |               `---[7.41% 0.038062ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.dialogue.VoiceCollectNodeDialogueActionRunner:buildShowVoiceTextResult() #124\n" +
                "        |       |                       `---[95.51% 15.086239ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.runner.AbstractActionRunner:postActionRun() #36\n" +
                "        |       +---[0.35% min=0.256203ms,max=0.490825ms,total=0.747028ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.SopFlowEngine:updateRunningCondition() #87\n" +
                "        |       +---[0.17% min=0.004977ms,max=0.345092ms,total=0.350069ms,count=2] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.SopFlowEngine:updateFlowExecuteContext() #89\n" +
                "        |       `---[0.02% 0.050703ms ] com.didiglobal.kefu.fitan.web.biz.sopengine.engine.SopFlowEngine:buildFlowExecuteResult() #92\n" +
                "        `---[0.01% 0.021057ms ] com.didiglobal.kefu.spec.web.BaseResponse:make() #38";

        DefaultCategoryDataset dataset = parseLogData(logData);
        JFreeChart chart = ChartFactory.createBarChart(
                "Arthas Method Cost Analysis",
                "Methods",
                "Cost (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static DefaultCategoryDataset parseLogData(String logData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Pattern pattern = Pattern.compile("\\[(\\d+\\.?\\d*)ms\\].*(.*):\\w+\\(.*\\)");
        Matcher matcher = pattern.matcher(logData);

        while (matcher.find()) {
            double cost = Double.parseDouble(matcher.group(1));
            String method = matcher.group(2).trim();
            dataset.addValue(cost, "Cost", method);
        }

        return dataset;
    }
}