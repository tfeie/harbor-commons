package com.the.harbor.test;

import java.util.Set;

import com.the.harbor.commons.redisdata.util.HyGoUtil;

public class HyGoCommentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String goId="6C178C83BBA244A89C765496F0EFAFBF";
		Set<String> set = HyGoUtil.getGoCommentIds(goId, 0, -1);
		for (String commentId : set) {
			HyGoUtil.deleteGoComment(commentId);
			HyGoUtil.deleteGoCommentId(goId, commentId);
		}

	}

}
