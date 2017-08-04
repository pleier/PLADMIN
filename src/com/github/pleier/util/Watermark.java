package com.github.pleier.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/** 
 * ˵����ͼƬˮӡ������ ������ע�⣺�ð�װ���jdk����Ҫ�ÿ��������Դ���jdk��
 * �����ˣ�plei
 * �޸�ʱ�䣺2017��8��4��
 * @version
 */
public class Watermark {

		private	static String strFWATERM,strIWATERM;

		static{
			strFWATERM = Tools.readTxtFile(Const.FWATERM);	//��ȡ����ˮӡ����
			strIWATERM = Tools.readTxtFile(Const.IWATERM);	//��ȡͼƬˮӡ����
		}

		/**
		 * ˢ��
		*/
		public static void fushValue(){
			strFWATERM = Tools.readTxtFile(Const.FWATERM);	//��ȡ����ˮӡ����
			strIWATERM = Tools.readTxtFile(Const.IWATERM);	//��ȡͼƬˮӡ����
		}

		/**
		 * @param imagePath ͼƬȫ·��
		*/
	  	public static void setWatemark(String imagePath){
	  		//����ˮӡ
	  		if(null != strFWATERM && !"".equals(strFWATERM)){
				String strFW[] = strFWATERM.split(",fh,");
				if(strFW.length == 5){
					if("yes".equals(strFW[0])){
						pressText(strFW[1].toString(), imagePath, "", 1, Color.RED,Integer.parseInt(strFW[2]), Integer.parseInt(strFW[3]), Integer.parseInt(strFW[4]));	//����
					}
				}
			}
	  		//ͼƬˮӡ
			if(null != strIWATERM && !"".equals(strIWATERM)){
				String strIW[] = strIWATERM.split(",pl,");
				if(strIW.length == 4){
					if("yes".equals(strIW[0])){
						pressImage(PathUtil.getClasspath() + Const.FILEPATHIMG+strIW[1], imagePath, Integer.parseInt(strIW[2]), Integer.parseInt(strIW[3]));
					}
				}
			}
		  }

	    /**
	     * ��ͼƬӡˢ��ͼƬ��
	     *
	     * @param pressImg --
	     *            ˮӡ�ļ�
	     * @param targetImg --
	     *            Ŀ���ļ�
	     * @param x
	     *            --x����
	     * @param y
	     *            --y����
	     */
	    public final static void pressImage(String pressImg, String targetImg,
	            int x, int y) {
	        try {
	            //Ŀ���ļ�
	            File _file = new File(targetImg);
	            Image src = ImageIO.read(_file);
	            int wideth = src.getWidth(null);
	            int height = src.getHeight(null);
	            BufferedImage image = new BufferedImage(wideth, height,
	                    BufferedImage.TYPE_INT_RGB);
	            Graphics g = image.createGraphics();
	            g.drawImage(src, 0, 0, wideth, height, null);

	            //ˮӡ�ļ�
	            File _filebiao = new File(pressImg);
	            Image src_biao = ImageIO.read(_filebiao);
	            int wideth_biao = src_biao.getWidth(null);
	            int height_biao = src_biao.getHeight(null);
	            //g.drawImage(src_biao, (wideth - wideth_biao) / 2,(height - height_biao) / 2, wideth_biao, height_biao, null);
	            g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);
	            //ˮӡ�ļ�����
	            g.dispose();
	            FileOutputStream out = new FileOutputStream(targetImg);
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	            encoder.encode(image);
	            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * ��ӡ����ˮӡͼƬ
	     *
	     * @param pressText
	     *            --����
	     * @param targetImg --
	     *            Ŀ��ͼƬ
	     * @param fontName --
	     *            ������
	     * @param fontStyle --
	     *            ������ʽ
	     * @param color --
	     *            ������ɫ
	     * @param fontSize --
	     *            �����С
	     * @param x --
	     *            ƫ����
	     * @param y
	     */

	    public static void pressText(String pressText, String targetImg,
	    		String fontName, int fontStyle, Color color, int fontSize, int x,int y) {
	        try {
	            File _file = new File(targetImg);
	            Image src = ImageIO.read(_file);
	            int wideth = src.getWidth(null);
	            int height = src.getHeight(null);
	            BufferedImage image = new BufferedImage(wideth, height,
	                    BufferedImage.TYPE_INT_RGB);
	            Graphics g = image.createGraphics();
	            g.drawImage(src, 0, 0, wideth, height, null);
	            g.setColor(color);
	            g.setFont(new Font(fontName, fontStyle, fontSize));
	            g.drawString(pressText, x, y);
	            g.dispose();
	            FileOutputStream out = new FileOutputStream(targetImg);
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	            encoder.encode(image);
	            out.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }  
}
