package itw.ucl.selenium.ITW_selenium.src;

import com.google.gson.annotations.SerializedName;

public class UQuestion {

	@SerializedName("uquestionId")
	private long questionId;

	// getters and setter methods
	public long getUQuestionId() {
		return questionId;
	}

	public void setUQuestionId(long questionId) {
		this.questionId = questionId;
	}

}
