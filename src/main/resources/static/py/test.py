import sys
# from docx import Document
# from docx.shared import RGBColor, Pt
# from docx.oxml.ns import qn

if __name__ == '__main__':
    # stateStr = sys.argv[1]
    # number = int(stateStr) + 5
    document = Document('D:\work\javaidea\Project\problemSystem\src\main\\resources\static\py\学校模板.docx')

    point1 = document.add_picture('得分.bmp')
    savePath = "D:\\"
    document.save(savePath + "over.docx")

    # print("Python ren: " + stateStr)
