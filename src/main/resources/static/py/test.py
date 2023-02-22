import sys
# from docx import Document
# from docx.shared import RGBColor, Pt
# from docx.oxml.ns import qn

if __name__ == '__main__':
    # stateStr = sys.argv[1]
    # number = int(stateStr) + 5
    document = Document('E:\\code\\git\\problemSystem-K\\src\main\\resources\\static\\py\\学校模板.docx')

    point1 = document.add_picture('得分.bmp')
    savePath = "C:\\Users\\l\\Desktop\\"
    document.save(savePath + "over.docx")

    # print("Python ren: " + stateStr)
