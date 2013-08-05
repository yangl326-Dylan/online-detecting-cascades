package Source;

public class PreviousMiddleSequence4PostSequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		previousMiddleSequence4PostSequence("abcdef","badcef");
	}
	/**
	 * 给定先序和中序 求二叉树的后序问题
	 * @param preStr
	 * @param midStr
	 */
	public static void previousMiddleSequence4PostSequence(String preStr, String midStr)
	{
		//简单的验证下输入是否符合规则
		if(preStr.length()!=midStr.length())
		{
			System.out.println("Input Error !");
			return ;
		}
		//基本问题之一
		if(preStr.length()<=0 || midStr.length()<=0)
			return ;
		//基本问题二
		if(preStr.length()==1 && midStr.length()==1 && preStr.equals(midStr) )
		{
			System.out.print(preStr);
			return ;
		}
		char root=preStr.charAt(0);
		int index=midStr.indexOf(root);
		//找到对应的左子树的中序和先序序列
		String leftStr=midStr.substring(0, index);
		String preSubStr1=preStr.substring(1, leftStr.length()+1);
		//找到对应的右子树的中序和先序序列
		String rightStr=midStr.substring(index+1, midStr.length());
		String preSubStr2=preStr.substring(1+leftStr.length());
		//递归调用，注意顺序，先调用右子树的
		previousMiddleSequence4PostSequence(preSubStr2,rightStr);
		previousMiddleSequence4PostSequence(preSubStr1,leftStr);
		System.out.print(root);
	}
}
