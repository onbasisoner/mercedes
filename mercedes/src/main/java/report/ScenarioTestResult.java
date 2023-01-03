package report;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ScenarioTestResult {

    public String environment;
    public String customData;
    public boolean status;
    public String user;
    public String branch;
    public int runId;
    public List<Integer> caseId;
    public String errorMessage;
    public String os;
    public String deviceModel;
    public String appVersion;
    public String video;
    public String screenShot;
    public String className;
    public String methodName;
    public String projectName;
    public String subTaskId;
    public boolean isDataProvider;
    public int dataProviderIndex;
    public Object[] dataProviderParameters;
    public String rerunScenarioId;
    public boolean isRerun;
    public Date startTime;
    public Date endTime;

    public List<Step> steps;

    private ScenarioTestResult(Builder builder) {
        environment = builder.environment;
        customData = builder.customData;
        status = builder.status;
        user = builder.user;
        branch = builder.branch;
        runId = builder.runId;
        caseId = builder.caseId;
        errorMessage = builder.errorMessage;
        os = builder.os;
        deviceModel = builder.deviceModel;
        appVersion = builder.appVersion;
        video = builder.video;
        screenShot = builder.screenShot;
        className = builder.className;
        methodName = builder.methodName;
        projectName = builder.projectName;
        subTaskId = builder.subTaskId;
        isDataProvider = builder.isDataProvider;
        dataProviderIndex = builder.dataProviderIndex;
        dataProviderParameters = builder.dataProviderParameters;
        rerunScenarioId = builder.rerunScenarioId;
        isRerun = builder.isRerun;
        startTime = builder.startTime;
        endTime = builder.endTime;
        steps = builder.steps;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String environment;
        private String customData;
        private boolean status;
        private String user = System.getProperty("user.name");
        private String branch;
        private int runId = 0;
        private List<Integer> caseId;
        private String errorMessage;
        private String os = System.getProperty("os.name");
        private String deviceModel;
        private String appVersion;
        private String video;
        private String screenShot;
        private String className;
        private String methodName;
        private String projectName = "getir-web-automation";
        private String subTaskId;
        private boolean isDataProvider = false;
        private int dataProviderIndex;
        private Object[] dataProviderParameters;
        private String rerunScenarioId;
        private boolean isRerun = false;
        private Date startTime;
        private Date endTime;

        private List<Step> steps;

        private Builder() {}

        public Builder withEnvironment(String val) {
            environment = val;
            return this;
        }

        public Builder withCustomData(String val) {
            customData = val;
            return this;
        }

        public Builder withStatus(boolean val) {
            status = val;
            return this;
        }

        public Builder withUser(String val) {
            user = val;
            return this;
        }

        public Builder withBranch(String val) {
            branch = val;
            return this;
        }

        public Builder withRunId(int val) {
            runId = val;
            return this;
        }

        public Builder withCaseId(List<Integer> val) {
            caseId = val;
            return this;
        }

        public Builder withErrorMessage(String val) {
            errorMessage = val;
            return this;
        }

        public Builder withOs(String val) {
            os = val;
            return this;
        }

        public Builder withDeviceModel(String val) {
            deviceModel = val;
            return this;
        }

        public Builder withAppVersion(String val) {
            appVersion = val;
            return this;
        }

        public Builder withVideo(String val) {
            video = val;
            return this;
        }

        public Builder withScreenShot(String val) {
            screenShot = val;
            return this;
        }

        public Builder withClassName(String val) {
            className = val;
            return this;
        }

        public Builder withMethodName(String val) {
            methodName = val;
            return this;
        }

        public Builder withProjectName(String val) {
            projectName = val;
            return this;
        }

        public Builder withSubTaskId(String val) {
            subTaskId = val;
            return this;
        }

        public Builder withIsDataProvider(boolean val1, int val2, Object[] val3) {
            isDataProvider = val1;
            if (val1) {
                dataProviderIndex = val2;
                dataProviderParameters = val3;
            }
            return this;
        }

        public Builder withRerunScenarioId(String val) {
            if(!Objects.equals(val, ""))
                rerunScenarioId = val;
            if (val != null && !val.equals(""))
                isRerun = true;
            return this;
        }

        public Builder withStartTime(Date val) {
            startTime = val;
            return this;
        }

        public Builder withEndTime(Date val) {
            endTime = val;
            return this;
        }

        public Builder withSteps(List<Step> val) {
            steps = val;
            return this;
        }

        public ScenarioTestResult build() {
            return new ScenarioTestResult(this);
        }
    }

    public static class Step {

        private int index;
        private String step_name;
        private String status;

        public Step(String step_name, String status) {
            this.step_name = step_name;
            this.status = status;
        }
        public Step(int index, String step_name) {
            this.index = index;
            this.step_name = step_name;
        }

        public Step(String step_name) {
            this.step_name = step_name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getStep_name() {
            return step_name;
        }

        public void setStep_name(String step_name) {
            this.step_name = step_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
