package Trees;

public class BTreeNode<T> {

	int keyNum;//less than m
	BTreeNode parent;
	BTreeNode[] childrenPtr;
	Object[] key;
	public BTreeNode(BTreeNode par, int _keyNum, BTreeNode[] ptr, T[] _key)
	{
		if(_keyNum>=Constants.M)
		{
			try {
				throw new Exception("larger then order m: "+ Constants.M);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		keyNum = _keyNum;
		parent = par;
		childrenPtr= ptr;
		key = _key;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
